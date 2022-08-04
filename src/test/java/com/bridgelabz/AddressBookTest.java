package com.bridgelabz;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class AddressBookTest {
    package com.bridgelabz.addressbooksystemjdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bridgelabz.addressbooksystemjdbc.AddressBook.IOService;


    public class AddressBookTest {


        static AddressBook addressBook = new AddressBook();

        @BeforeClass
        public static void createAddressBookObject() {
            addressBook.setAddressBookName("book1");
        }

        @Test
        public void givenDetails_ShouldAddToContactList() {

            ContactPerson person = new ContactPerson();


            String firstName = "Ashika";
            String lastName = "Satish";
            String email = "ashika@gmail.com";
            long phoneNumber = 938476387;
            String city = "Bangalore";
            String state = "Karnataka";
            long zipCode = 560043;
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setEmail(email);
            person.setPhoneNumber(phoneNumber);
            person.address.setCity(city);
            person.address.setState(state);
            person.address.setZip(zipCode);

            addressBook.addContact(firstName, person);

            firstName = "Sagarika";
            lastName = "Satish";
            email = "sagarika@gmail.com";
            phoneNumber = 847648253;
            city = "Mangalore";
            state = "Karnataka";
            zipCode = 560043;
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setEmail(email);
            person.setPhoneNumber(phoneNumber);
            person.address.setCity(city);
            person.address.setState(state);
            person.address.setZip(zipCode);

            addressBook.addContact(firstName, person);

            int listSize = addressBook.contactList.size();
            Assert.assertEquals(2, listSize);

        }

        @Test
        public void given2Contacts_WhenWrittenToFile_ShouldMatchEntries()
        {
            AddressBookFileIO addressFileIO = new AddressBookFileIO();
            addressFileIO.writeToAddressBookFile("book1.txt", addressBook.contactList);
            addressFileIO.printData("book1.txt");
            long entries = addressFileIO.countEntries("book1.txt");
            Assert.assertEquals(2, entries);

        }

        @Test
        public void givenFile_WhenRead_ShouldReturnNumberOfEntries() {

            AddressBookFileIO addressFileIO = new AddressBookFileIO();
            List<String> entries = addressFileIO.readDataFromFile("book1.txt");
            long countEntries = entries.size();
            Assert.assertEquals(2, countEntries);
        }

        @Test
        public void givenAddressBookInDB_WhenRetrieved_ShouldMatchCountOfAddressBooks(){

            AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
            Map<Integer, String> addressbookList = addressBookDirectory.readAddressDetails(IOService.DB_IO);
            Assert.assertEquals(2, addressbookList.size());
        }

        @Test
        public void givenContactInDB_WhenRetrieved_ShouldMatchContactCount(){

            AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
            List<ContactPerson> contactdetailsList = addressBookDirectory.readContactDetails(IOService.DB_IO);
            Assert.assertEquals(7, contactdetailsList.size());
        }


        @Test
        public void givenCity_WhenMatches_ShouldReturnEmployeeDetails() {

            AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
            String city = "Bangalore";
            List<ContactPerson> contactList = addressBookDirectory.getEmployeeDetailsBasedOnCity(IOService.DB_IO, city);
            Assert.assertEquals(2, contactList.size());
        }

        @Test
        public void givenState_WhenMatches_ShouldReturnEmployeeDetails() {

            String state = "New York";
            AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
            List<ContactPerson> contactList = addressBookDirectory.getEmployeeDetailsBasedOnState(IOService.DB_IO, state);
            Assert.assertEquals(2, contactList.size());
        }

        @Test
        public void givenAddressBookInDB_ShouldReturnCountOfBasedOnCity() {

            AddressBookDirectory employeePayrollService = new AddressBookDirectory();
            List<Integer> expectedCountBasedOnGender = new ArrayList();
            expectedCountBasedOnGender.add(1);
            expectedCountBasedOnGender.add(2);
            expectedCountBasedOnGender.add(1);
            expectedCountBasedOnGender.add(1);
            expectedCountBasedOnGender.add(1);
            expectedCountBasedOnGender.add(1);
            List<Integer> maximumSalaryBasedOnGender = employeePayrollService.getCountOfEmployeesBasedOnCity(IOServicevice.DB_IO);
            if(maximumSalaryBasedOnGender.size() == 6) {
                Assertions.assertEquals(expectedCountBasedOnGender, maximumSalaryBasedOnGender);
            }
        }

        @Test
        public void givenAddressBookInDB_ShouldReturnCountOfBasedOnState() {

            AddressBookDirectory employeePayrollService = new AddressBookDirectory();
            List<Integer> expectedCountBasedOnGender = new ArrayList();
            expectedCountBasedOnGender.add(1);
            expectedCountBasedOnGender.add(4);
            expectedCountBasedOnGender.add(2);
            List<Integer> maximumSalaryBasedOnGender = employeePayrollService.getCountOfEmployeesBasedOnState(AddressBook.IOService.DB_IO);
            if(maximumSalaryBasedOnGender.size() == 3) {
                Assertions.assertEquals(expectedCountBasedOnGender, maximumSalaryBasedOnGender);
            }
        }

    }
}
