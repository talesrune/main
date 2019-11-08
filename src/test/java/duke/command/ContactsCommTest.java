package duke.command;

import duke.enums.ErrorMessages;
import duke.task.ContactList;
import duke.task.Contacts;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author e0318465
/**
 * Tests to check whether all the contacts related functionalities are working.
 */
public class ContactsCommTest {

    @Test
    void contactsTest() {
        TaskList items = new TaskList();
        Ui ui = new Ui();
        ContactList contactList = new ContactList();
        Contacts contactObj1 = new Contacts("Prof Tan", "91234567", " ", "E1-08-11");
        Command cmd1 = new AddContactsCommand(contactObj1, contactList);
        assertEquals("\n     Got it, now you have 1 contacts. Contact added:\n"
                + "     Name: Prof Tan\n"
                + "     Number: 91234567\n"
                + "     Email: \n"
                + "     Office: E1-08-11", cmd1.executeGui(items, ui));

        Contacts contactObj2 = new Contacts("Prof Tan", "91234567", "tancc@nus.edu.sg", "E1-08-11");
        Command cmd2 = new AddContactsCommand(contactObj2, contactList);
        assertEquals("\n     Got it, now you have 2 contacts. Contact added:\n"
                + "     Name: Prof Tan\n"
                + "     Number: 91234567\n"
                + "     Email: tancc@nus.edu.sg\n"
                + "     Office: E1-08-11", cmd2.executeGui(items, ui));

        Command cmd3 = new DeleteContactCommand(1, contactList);
        assertEquals("Now you have 1 contact(s). I've removed this contact:\n"
                + "     Name: Prof Tan\n"
                + "     Number: 91234567\n"
                + "     Email: \n"
                + "     Office: E1-08-11", cmd3.executeGui(items, ui));

        Command cmd4 = new ListContactsCommand(contactList);
        assertEquals("Here are all your contacts:\n"
                + "     1. Name: Prof Tan, 91234567, tancc@nus.edu.sg, E1-08-11\n", cmd4.executeGui(items, ui));

        Command cmd5 = new FindContactCommand("prof tan", contactList);
        assertEquals("     Here are the matching contacts in your list:\n"
                + "     Name: Prof Tan, 91234567, tancc@nus.edu.sg, E1-08-11\n", cmd5.executeGui(items, ui));

        Command cmd6 = new FindContactCommand("Lester", contactList);
        assertEquals("     Here are the matching contacts in your list:\n"
                + "     No matching tasks found.", cmd6.executeGui(items, ui));
    }

    @Test
    void deleteContactsTest_exceptionThrown() {
        ContactList contactList = new ContactList();
        Contacts contactObj1 = new Contacts("Alexa", "", "", "");
        new AddContactsCommand(contactObj1, contactList);

        String nonIntegerInput = "abc";
        try {
            new DeleteContactCommand(Integer.parseInt(nonIntegerInput), contactList);
        } catch (NumberFormatException e) {
            assertEquals("     Input is not an integer value!", ErrorMessages.NON_INTEGER_ALERT.message);
        }

        String emptyKeyword = "";
        try {
            new FindContactCommand(emptyKeyword, contactList);
        } catch (Exception e) {
            assertEquals("     (>_<) OOPS!!! The keyword cannot be empty.", ErrorMessages.KEYWORD_IS_EMPTY.message);
        }
    }

    @Test
    void addContactsTest_exceptionThrown() {
        ContactList contactList = new ContactList();
        Contacts contactObj1 = new Contacts("Alexa", "Nil", "isValid@gmail.com", "Nil");
        try {
            new AddContactsCommand(contactObj1, contactList);
        } catch (NumberFormatException e) {
            assertEquals("Format is in: addcontact <name>, <contact>, <email>, <office>\n"
                    + "Put 'Nil' if field is empty\n"
                    + "Check that email has an '@'", ErrorMessages.CONTACT_FORMAT.message);
        }
    }
}