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
package sensos.util.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Provides all Countries that are listed in JRE.
 * @author sensos
 */
public final class CountryChecker {

    private static HashMap<String, Country> countries = new HashMap<String, Country>();

    static {

        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {

            String iso = "";
            String code = "";
            String name = "";

            try {

                code = locale.getCountry();           
                name = locale.getDisplayCountry();
                iso = locale.getISO3Country();

            } catch (Exception e) {
                //swallow the error.
            } finally {
            }


            if (!"".equals(iso) && !"".equals(code) && !"".equals(name)) {
                countries.put(iso, new Country(iso, code, name));
            }
        }

    }

    public static HashMap<String, Country> getCountries() {

        return countries;

    }

    public static void main(String[] args) {
        
        List<Country> countries = new ArrayList<Country>();

        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            String iso = "";
            String code = "";
            String name = "";

            try {

                code = locale.getCountry();           
                name = locale.getDisplayCountry();
                iso = locale.getISO3Country();

            } catch (Exception e) {
                //swallow the error.
            } finally {
            }


            if (!"".equals(iso) && !"".equals(code) && !"".equals(name)) {
                
            System.out.println("Adding... " + iso + " " + code + " " + name);
                countries.add(new Country(iso, code, name));
            }
        }

        Collections.sort(countries, new CountryComparator());
        for (Country country : countries) {
            System.out.println(country);
        }
    }
}
