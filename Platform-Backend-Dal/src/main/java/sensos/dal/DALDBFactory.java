/**
 *  Sensos IoT Platform.
 *  Copyright (C) 2015 sensos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sensos.dal;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Represents the DALDBFactory that contains different DAO-Objects.
 *
 * @author sensos
 */
public abstract class DALDBFactory {

    public DALDBFactory() {
    }

    private Logger l = Logger.getLogger(this.getClass().getName());

    public boolean isOpen() {

        if (factory == null) {
            return false;
        } else {
            return factory.isOpen();
        }
    }



    public EntityManagerFactory factory;

    /**
     * Create DALDBFactory according to type definition that is associated with 'persistence.xml'.
     * @param type 
     */
    public DALDBFactory(DALDBFactoryType type) {

        createFactory(type);

    }

    /**
     * Create DALDBFactory according to associated persistence.xml where connection details may vary.
     * @param puname
     * @param username
     * @param password
     * @param url
     * @param driver 
     */
    public DALDBFactory(String puname, String username, String password, String url, String driver) {

        l.log(Level.INFO, "Creating {0} with address {1} and with driver {2}.", new Object[]{DALDBFactory.class.getName(), url, driver});

        boolean created = false;

        try {
            createFactory(puname, username, password, url, driver);
            created = true;
        } catch (Exception e) {

            created = false;
            l.log(Level.SEVERE, "Could not create a database connection: {0}", e.getMessage());

        }

    }

    /**
     * Create DALDBFactory with model parameter that is associated with DALDBFactoryType-definitions".
     * @param mode 
     */
    public DALDBFactory(String mode) {

        l.log(Level.INFO, "Creating {0} with mode {1}.", new Object[]{DALDBFactory.class.getName(), mode});

        boolean created = false;

        for (DALDBFactoryType m : DALDBFactoryType.values()) {
            if (m.toString().equals(mode)) {
                createFactory(m);
                created = true;
            }
        }

        if (!created) {
            throw new IllegalStateException("No such mode found for: " + mode);
        }

    }

    private void createFactory(DALDBFactoryType mode) {

        if (mode == null) {
            throw new IllegalStateException("Incoming mode cannot be null.");
        }

        l.log(Level.INFO, "Creating factory: {0}", mode.toString());

        factory = Persistence.createEntityManagerFactory(mode.toString());

    }

    private void createFactory(String puname, String user, String password, String url, String driver) {

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", user);
        properties.put("javax.persistence.jdbc.password", password);
        properties.put("javax.persistence.jdbc.driver", driver);
        properties.put("javax.persistence.jdbc.url", url);

        factory = Persistence.createEntityManagerFactory(puname, properties);

    }


    @Override
    protected void finalize() throws Throwable {

        l.log(Level.INFO, "Closing {0}...", DALDBFactory.class.getName());

        if (factory != null && factory.isOpen()) {
            factory.close();
        }

        super.finalize();

    }

}
