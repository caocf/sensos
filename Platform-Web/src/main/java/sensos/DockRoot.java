package sensos;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author sensos
 */
public class DockRoot {
    
    /**
     * Gets  root-directory where the instance is run.
     * @return 
     */
    public static String getDocRoot() {
        File docroot1 = new File("../docroot");

        try {
            docroot1 = docroot1.getCanonicalFile();
        } catch (IOException e) {
            docroot1 = docroot1.getAbsoluteFile();
        }
        return docroot1.getPath();
    } 

}
