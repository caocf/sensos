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
package sensos.dal.billing;

/**
 *
 * @author sensos
 */
public class SQLClauses {
    
    public static final String SQL_SELECT_CUSTOMER_BY_USERNAME = 
            
    "SELECT a.id,               password,       a.deleted, "      +
    "       status_id,          language_id,    entity_id, "    +
    "       failed_attempts,    user_name,      last_login, "   +
    "       last_status_change, a.create_datetime,currency_id, "  +
    "       subscriber_status,  a.optlock " + 
    "FROM   base_user a LEFT JOIN contact b ON (a.id = b.user_id) WHERE user_name = ?";

    public static final String SQL_SELECT_CUSTOMER_BY_ID = 
            
    "SELECT a.id,               password,       a.deleted, "      +
    "       status_id,          language_id,    entity_id, "    +
    "       failed_attempts,    user_name,      last_login, "   +
    "       last_status_change, a.create_datetime,currency_id, "  +
    "       subscriber_status,  a.optlock " + 
    "FROM   base_user a LEFT JOIN contact b ON (a.id = b.user_id) WHERE a.id = ?";
    
    public static final int COL_CUSTOMER_ID     = 0;
    public static final int COL_CUSTOMER_PWD    = 1;
    public static final int COL_CUSTOMER_DEL    = 2;
    public static final int COL_CUSTOMER_STAT   = 3;
    public static final int COL_CUSTOMER_LANG   = 4;
    public static final int COL_CUSTOMER_ENT    = 5;
    public static final int COL_CUSTOMER_FAIL   = 6;
    public static final int COL_CUSTOMER_NAM    = 7;
    public static final int COL_CUSTOMER_LASTLOG= 8;
    public static final int COL_CUSTOMER_LAST_STATUS_CHANGE = 9;
    public static final int COL_CUSTOMER_CREATE = 10;
    public static final int COL_CUSTOMER_CURRENCY = 11;
    public static final int COL_CUSTOMER_SUBSCRIBE_STATUS = 12;
    public static final int COL_CUSTOMER_OPTLOCK = 13;
    
    public static final String SQL_INSERT_CUSTOMER  = 
            
    "INSERT INTO base_user " + 
    "      (id,                 password,       deleted, "      +
    "       status_id,          language_id,    entity_id, "    +
    "       failed_attempts,    user_name,      last_login, "   +
    "       last_status_change, create_datetime,currency_id, "  +
    "       subscriber_status,  optlock) " + 
    "VALUES " + 
   "        (?,                  ?,              ?,"             +
   "         ?,                  ?,              ?,"             +
   "         ?,                  ?,              ?,"             +
   "         ?,                  ?,              ?,"             +
   "         ?,                  ?)";
    
    public static final String SQL_UPDATE_CUSTOMER_STATUS  = "UPDATE base_user SET status_id = 0 WHERE id = ?";
    
    public static final String SQL_SELECT_CONTACT = 
            
    "SELECT id,                  organization_name,          street_addres1, "       +
    "       street_addres2,      city,                       state_province, "       +
    "       postal_code,         country_code,               last_name,      "       +
    "       first_name,          person_initial,             person_title,   "       +
    "       phone_country_code,  phone_area_code,            phone_phone_number, "   +
    "       fax_country_code,    fax_area_code,              fax_phone_number, "     +
    "       email,               create_datetime,            deleted, "              +
    "       notification_include,user_id,                    optlock "               +
    "FROM contact WHERE user_id = ?";
    
public static final int COL_CONTACT_ID = 0;
public static final int COL_CONTACT_ORGNAME = 1;
public static final int COL_CONTACT_STREET_ADDRESS1 = 2;
public static final int COL_CONTACT_STREET_ADDRESS2 = 3;
public static final int COL_CONTACT_CITY = 4;
public static final int COL_CONTACT_PROVINCE = 5;
public static final int COL_CONTACT_POSTALCODE = 6;
public static final int COL_CONTACT_COUNTRY_CODE = 7;
public static final int COL_CONTACT_LASTNAME = 8;
public static final int COL_CONTACT_FIRSTNAME = 9;
public static final int COL_CONTACT_INITIAL = 10;
public static final int COL_CONTACT_TITLE = 11;
public static final int COL_CONTACT_P_COUNTRYCODE = 12;
public static final int COL_CONTACT_P_AREACODE = 13;
public static final int COL_CONTACT_P_NUMBER = 14;
public static final int COL_CONTACT_F_COUNTRYCODE = 15;
public static final int COL_CONTACT_F_AREACODE = 16;
public static final int COL_CONTACT_F_NUMBER = 17;
public static final int COL_CONTACT_EMAIL = 18;
public static final int COL_CONTACT_CREATE = 19;
public static final int COL_CONTACT_DELETED = 20;
public static final int COL_CONTACT_NOTIFICATION = 21;
public static final int COL_CONTACT_USER_ID=22;
public static final int COL_CONTACT_OPTLOCK=23;
    
    public static final String SQL_INSERT_CONTACT   = 
            
    "INSERT INTO contact " + 
    "      (id,                  organization_name,          street_addres1, "       +
    "       street_addres2,      city,                       state_province, "       +
    "       postal_code,         country_code,               last_name,      "       +
    "       first_name,          person_initial,             person_title,   "       +
    "       phone_country_code,  phone_area_code,            phone_phone_number, "   +
    "       fax_country_code,    fax_area_code,              fax_phone_number, "     +
    "       email,               create_datetime,            deleted, "              +
    "       notification_include,user_id,                    optlock )"              +
    "VALUES " + 
   "       (?,                   ?,                          ?,"                     +
   "        ?,                   ?,                          ?,"                     +
   "        ?,                   ?,                          ?,"                     +
   "        ?,                   ?,                          ?,"                     +
   "        ?,                   ?,                          ?,"                     +
   "        ?,                   ?,                          ?,"                     +
   "        ?,                   ?,                          ?,"                     +            
   "        ?,                   ?,                          ?)";
    
    public static final String SQL_UPDATE_CONTACT   = "UPDATE contact SET {0} = ? WHERE id = ?";
    
    public static final String SQL_INSERT_CONTACT_MAP = 

    "INSERT INTO contact_map " + 
    "      (id,                 contact_id,                 type_id,    "            +
    "       table_id,           foreign_id,                 optlock)    "            +
    "VALUES " + 
    "      (?,                  ?,                          ?,"                      +
    "       ?,                  ?,                          ?)";
    
    public static final String SQL_INSERT_CONTACT_TYPE =
            
    "INSERT INTO contact_type " + 
    "      (id,                 entity_id,                  is_primary,             optlock)" +
    "VALUES " + 
    "      (?,                  ?,                          ?,                      ?)";

    public static String SQL_MAX_CUSTOMER = "SELECT MAX(id) + 1 FROM base_user";

    public static String SQL_MAX_CONTACT = "SELECT MAX(id) + 1 FROM contact";

    
}
