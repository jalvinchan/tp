package seedu.duke.command;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.email.*;
import seedu.duke.utilities.Storage;
import seedu.duke.utilities.Ui;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class EditCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Email> emails = new ArrayList<>();
    private EmailManager emailManager = new EmailManager();
    private Ui ui = new Ui();
    private Storage storage = new Storage();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        emails.add(new Draft("testC@gmail.com", "456@gmail.com", "S1", "2020-4-23+01:00", "C1"));
        emails.add(new Draft("testA@gmail.com", "456@gmail.com", "S2", "2020-4-30+01:00", "C2"));
        emails.add(new Draft("testB@gmail.com", "456@gmail.com", "S3", "2019-4-23+03:00", "C3"));
        EmailManager.setEmailsList(emails);
    }

    @Test
    void execute_Edit_success() {
        new EditCommand("edit 1").execute(emailManager, ui, storage);
        String expectedOutput = "What would you like to edit? It must be one of [to, subject, content].";
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
