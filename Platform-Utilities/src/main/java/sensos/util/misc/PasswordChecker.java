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

/**
 * 
 * @author sensos
 */
public final class PasswordChecker {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private PasswordChecker() { }

    /**
     * Checks whether the given String satisfies the CSE department criteria for
     * a valid password. Prints an appropriate message to the given output
     * stream.
     *
     * @param s the String to check
     * @param out the output stream
     */
    public static boolean checkPassword(String s) {

        if(s==null||s.equals(""))
            return false;
        
        boolean length = correctLength(s);
        //boolean upper = containsUpperCaseLetter(s);
        //boolean lower = containsLowerCaseLetter(s);
        //boolean digit = containsDigits(s);

        //return length && upper && lower && digit;
        
        //return length && digit;
        
        return length;

    }

    /**
     * Checks if the given String contains at least 6 characters.
     *
     * @param s the String to check
     * @return true if s contains at least 6 characters, false otherwise
     */
    private static boolean correctLength(String s) {
        boolean result = true;
        if (s.length() < 6) {
            result = false;
        }
        return result;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean result = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param s the String to check
     * @return true if s contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean result = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param s the String to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsDigits(String s) {
        boolean result = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                result = true;
                break;
            }
        }
        return result;
    }

}
