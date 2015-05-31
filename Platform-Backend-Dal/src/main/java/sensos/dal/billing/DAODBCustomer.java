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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import sensos.bo.om.Customer;
import sensos.bo.om.CustomerContact;
import sensos.dal.DAODB;

/**
 *
 * @author sensos
 */
public class DAODBCustomer extends DAODB {

    public DAODBCustomer() {
    }

    public DAODBCustomer(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }

    /**
     * TODO
     *
     * @param customer
     */
    public void store(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer is null");
        }

        if (customer.getCustomerId() != null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer.id is  not null");
        }

        Query m = em.createNativeQuery(SQLClauses.SQL_MAX_CUSTOMER);

        int i = (int) m.getSingleResult();

        Long customerId = (long) i;
        String customerPassword = "eac16167cc0336669aaa432c85181af8";
        Integer customerDeleted = 0;
        Integer customerStatus = 1;
        Integer customerLanguageId = 1;
        Integer customerEntityId = 10;
        Integer customerFailedAttemps = 0;
        String customerName = customer.getUsername();
        Date customerLastLogin = new Date(new java.util.Date().getTime());
        Date customerLastStatusChange = new Date(new java.util.Date().getTime());
        Date customerCreate = new Date(new java.util.Date().getTime());
        Integer customerCurrency = 3;
        Integer customerSubscribeStatus = 9;
        Integer customerOptLock = 1;

        em.getTransaction().begin();

        Query q = em.createNativeQuery(SQLClauses.SQL_INSERT_CUSTOMER);

        q.setParameter(1, customerId);
        q.setParameter(2, customerPassword);
        q.setParameter(3, customerDeleted);
        q.setParameter(4, customerStatus);
        q.setParameter(5, customerLanguageId);
        q.setParameter(6, customerEntityId);
        q.setParameter(7, customerFailedAttemps);
        q.setParameter(8, customerName);
        q.setParameter(9, customerLastLogin);
        q.setParameter(10, customerLastStatusChange);
        q.setParameter(11, customerCreate);
        q.setParameter(12, customerCurrency);
        q.setParameter(13, customerSubscribeStatus);
        q.setParameter(14, customerOptLock);

        try {

            q.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        }

    }

    /**
     * TODO
     *
     * @param customer
     */
    public void disable(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer is null");
        }

        if (customer.getCustomerId() == null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer.id is null");
        }

        em.getTransaction().begin();

        Query q = em.createNativeQuery(SQLClauses.SQL_UPDATE_CUSTOMER_STATUS);

        q.setParameter(1, customer.getCustomerId());

        try {

            q.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        }

    }

    /**
     * TODO
     *
     * @param customer
     * @return
     */
    public Customer getCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer cannot be null");
        }



        Query q = null;
        
        if( customer.getCustomerId() != null ) {
            q = em.createNativeQuery(SQLClauses.SQL_SELECT_CUSTOMER_BY_ID);
            q.setParameter(1, customer.getCustomerId());
        }
        if( customer.getUsername() != null) {
            q = em.createNativeQuery(SQLClauses.SQL_SELECT_CUSTOMER_BY_USERNAME);
            q.setParameter(1, customer.getUsername());
        }

        

        try {

            List<Object[]> objectColumns = q.getResultList();

            if (objectColumns.size() > 1) {
                throw new IllegalStateException("Query returns multiple customers ");
            }
            
            if(objectColumns.isEmpty()) {
                return null;
            }

            Object[] objectColumn = objectColumns.get(0);

            Integer customerId = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_ID];
            //String customerPassword = (String) objectColumn[SQLClauses.COL_CUSTOMER_PWD];
            //Integer customerDeleted = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_DEL];
            //Integer customerStatus = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_STAT];
            //Integer customerLanguageId = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_LANG];
            //Integer customerEntityId = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_ENT];
            //Integer customerFailedAttemps = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_FAIL];
            String username = (String) objectColumn[SQLClauses.COL_CUSTOMER_NAM];
            //Date customerLastLogin = (Date) objectColumn[SQLClauses.COL_CUSTOMER_LASTLOG];
            //Date customerLastStatusChange = (Date) objectColumn[SQLClauses.COL_CUSTOMER_LAST_STATUS_CHANGE];
            //Date customerCreate = (Date) objectColumn[SQLClauses.COL_CUSTOMER_CREATE];
            //Integer customerCurrency = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_CURRENCY];
            //Integer customerSubscribeStatus = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_SUBSCRIBE_STATUS];
            //Integer customerOptLock = (Integer) objectColumn[SQLClauses.COL_CUSTOMER_OPTLOCK];

            customer.setCustomerId((long)customerId);
            customer.setUsername(username);

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

            return null;

        }

        return customer;

    }

    /**
     * TODO
     *
     * @param customer
     */
    public void update(Customer customer) {

        throw new IllegalStateException("Not yet supported");

        /*
         if (customer == null) {
         throw new IllegalArgumentException("Incoming PlatformCustomer cannot be null");
         }

         if (customer.getId() == null) {
         throw new IllegalArgumentException("PlatformCustomer.id is missing");
         }
        
         em.getTransaction().begin();

         Query q = em.createNativeQuery("tmbd");

         q.setParameter(1, "value");

         try {

         q.executeUpdate();
         em.getTransaction().commit();

         } catch (Exception e) {

         e.printStackTrace();
         em.getTransaction().rollback();

         }*/
    }

    /**
     * TODO
     *
     * @param contact
     */
    public void store(CustomerContact contact) {

        if (contact == null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer cannot be null");
        }

        if (contact.getId() != null) {
            throw new IllegalArgumentException("PlatformCustomer.id is already set");
        }

        em.getTransaction().begin();

        Query m = em.createNativeQuery(SQLClauses.SQL_MAX_CONTACT);

        int i = (int) m.getSingleResult();

        Query q = em.createNativeQuery(SQLClauses.SQL_INSERT_CONTACT);

        q.setParameter(1, i);                                                        //id
        q.setParameter(2, contact.getOrganization());                                 //organization name
        q.setParameter(3, contact.getStreetAddress1());                               //street address1

        q.setParameter(4, contact.getStreetAddress2());                               //street address2
        q.setParameter(5, contact.getCity());                                         //city
        q.setParameter(6, contact.getState());                                        //state or province

        q.setParameter(7, contact.getPostalCode());                                   //postal code
        q.setParameter(8, contact.getCountryCode());                                  //country code
        q.setParameter(9, contact.getLastName());                                     //last name

        q.setParameter(10, contact.getFirstName());                                    //first name
        q.setParameter(11, contact.getInitials());                                     //person initial
        q.setParameter(12, contact.getTitle());                                        //person title

        q.setParameter(13, contact.getPhoneCountryCode() == null ? 0 : contact.getPhoneCountryCode());                        //phone country code
        q.setParameter(14, contact.getPhoneAreaCode() == null ? 0 : contact.getPhoneAreaCode());                           //phone area code
        q.setParameter(15, contact.getPhoneNumber());                                   //phone number

        q.setParameter(16, 0);                                                 //fax country code    
        q.setParameter(17, 0);                                                 //fax area code
        q.setParameter(18, 0);                                                 //fax number

        q.setParameter(19, contact.getEmail());                                        //email
        q.setParameter(20, contact.getCreated());                                      //create datetime
        q.setParameter(21, contact.isRemoved() ? 0 : 1);                               //deleted

        q.setParameter(22, 0);                                                         //notification include
        q.setParameter(23, contact.getUserId());                                       //user id
        q.setParameter(24, 0);                                                         //optlock

        try {

            q.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        }

    }

    /**
     * TODO
     *
     * @param contact
     */
    public void update(CustomerContact contact) {

        if (contact == null) {
            throw new IllegalArgumentException("Incoming PlatformCustomer cannot be null");
        }

        if (contact.getId() == null) {
            throw new IllegalArgumentException("PlatformCustomer.id is missing");
        }

        throw new IllegalStateException("Not yet supported");

        /*
         em.getTransaction().begin();

         Query q = em.createNativeQuery(SQLClauses.SQL_UPDATE_CONTACT);

         q.setParameter(1, "value");

         try {

         q.executeUpdate();
         em.getTransaction().commit();

         } catch (Exception e) {

         e.printStackTrace();
         em.getTransaction().rollback();

         }*/
    }

    public List<CustomerContact> getCustomerContact(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Incoming customer cannot be null");
        }

        if (customer.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer.username is missing");
        }

        Query q = em.createNativeQuery(SQLClauses.SQL_SELECT_CONTACT);

        q.setParameter(1, customer.getCustomerId());

        ArrayList<CustomerContact> contacts = new ArrayList<>();

        CustomerContact contact = null;

        try {

            List<Object[]> resultColumns = q.getResultList();

            if (resultColumns.isEmpty()) {
                return null;
            }

            for (Object[] resultColumn : resultColumns) {

                contact = new CustomerContact();

                Integer contactId = (Integer) resultColumn[SQLClauses.COL_CONTACT_ID];
                String organizationName = (String) resultColumn[SQLClauses.COL_CONTACT_ORGNAME];
                String streetAddress1 = (String) resultColumn[SQLClauses.COL_CONTACT_STREET_ADDRESS1];
                String streetAddress2 = (String) resultColumn[SQLClauses.COL_CONTACT_STREET_ADDRESS2];
                String city = (String) resultColumn[SQLClauses.COL_CONTACT_CITY];
                String province = (String) resultColumn[SQLClauses.COL_CONTACT_PROVINCE];
                String postalCode = (String) resultColumn[SQLClauses.COL_CONTACT_POSTALCODE];
                String countryCode = (String) resultColumn[SQLClauses.COL_CONTACT_COUNTRY_CODE];
                String lastName = (String) resultColumn[SQLClauses.COL_CONTACT_LASTNAME];
                String firstName = (String) resultColumn[SQLClauses.COL_CONTACT_FIRSTNAME];
                String initial = (String) resultColumn[SQLClauses.COL_CONTACT_INITIAL];
                String title = (String) resultColumn[SQLClauses.COL_CONTACT_TITLE];

                //Phone
                Integer phoneCountryCode = (Integer) resultColumn[SQLClauses.COL_CONTACT_P_COUNTRYCODE];
                Integer phoneAreCode = (Integer) resultColumn[SQLClauses.COL_CONTACT_P_AREACODE];
                String phoneNumber = (String) resultColumn[SQLClauses.COL_CONTACT_P_NUMBER];

            //Fax
                //Integer countryCodeFax = (Integer) resultColumn[SQLClauses.COL_CONTACT_F_COUNTRYCODE];
                //Integer countryAreaFax = (Integer) resultColumn[SQLClauses.COL_CONTACT_F_AREACODE];
                //String fax = (String) resultColumn[SQLClauses.COL_CONTACT_F_NUMBER];
                //email
                String email = (String) resultColumn[SQLClauses.COL_CONTACT_EMAIL];

                java.sql.Timestamp contactCreate = (Timestamp) resultColumn[SQLClauses.COL_CONTACT_CREATE];
                Integer contactDeleted = (Integer) resultColumn[SQLClauses.COL_CONTACT_DELETED];
                //Integer contactNofification = (Integer) resultColumn[SQLClauses.COL_CONTACT_NOTIFICATION];
                Integer contactUserId = (Integer) resultColumn[SQLClauses.COL_CONTACT_USER_ID];
                //Integer contactOptLock = (Integer) resultColumn[SQLClauses.COL_CONTACT_OPTLOCK];

                contact.setId((long)contactId);
                contact.setOrganization(organizationName);
                contact.setStreetAddress1(streetAddress1);
                contact.setStreetAddress2(streetAddress2);
                contact.setCity(city);
                contact.setState(province);
                contact.setPostalCode(postalCode);
                contact.setCountryCode(countryCode);
                contact.setLastName(lastName);
                contact.setFirstName(firstName);
                contact.setIntials(initial);
                contact.setTitle(title);

                contact.setPhoneCountryCode(phoneCountryCode);
                contact.setPhoneAreaCode(phoneAreCode);
                contact.setPhoneNumber(phoneNumber);

                contact.setEmail(email);
                contact.setCreated(contactCreate);
                contact.setRemoved(contactDeleted != null && contactDeleted.equals(0));

                contact.setUserId((long)contactUserId);

                contacts.add(contact);

            }

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

            contact = null;

        }

        return contacts;

    }

}
