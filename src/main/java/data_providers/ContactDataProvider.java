package data_providers;

import dto.ContactDto;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactDataProvider {
    @DataProvider(name = "wrongContacts")
    public Iterator<ContactDto> dataProviderWrongContacts() {
        List<ContactDto> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/wrong_contact.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] fields = line.split(",", -1);
                if (fields.length != 6) {
                    throw new IllegalArgumentException(
                            "Wrong number of fields in CSV: " + line);
                }
                ContactDto contact = ContactDto.builder()
                        .id("1")
                        .name(fields[0])
                        .lastName(fields[1])
                        .email(fields[2])
                        .phone(fields[3])
                        .address(fields[4])
                        .description(fields[5])
                        .build();
                contacts.add(contact);
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot read wrong_contact.csv", e);
        }
        return contacts.iterator();
    }
}
