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

import java.text.Collator;
import java.util.Comparator;

/**
 * Puts Countries in alphabetical order.
 * @author sensos
 */
class CountryComparator implements Comparator<Country> {
  private Comparator comparator;

  CountryComparator() {
    comparator = Collator.getInstance();
  }

  public int compare(Country o1, Country o2) {
    return comparator.compare(o1.name, o2.name);
  }
}
