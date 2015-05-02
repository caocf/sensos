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
 * Representation of a Country.
 * @author sensos
 */
public class Country {
  private String iso;

  private String code;

  public String name;

  Country(String iso, String code, String name) {
    this.iso = iso;
    this.code = code;
    this.name = name;
  }

  
  public String toString() {

      String s = "{ Country { \"iso:\"" + iso;
           s+= ",\"code:\"" + code;
           s+= ",\"name:\"" + name + " } }";
           
           return s;

  }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
  
}