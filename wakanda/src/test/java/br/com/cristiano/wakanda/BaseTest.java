package br.com.cristiano.wakanda;

import br.com.cristiano.wakanda.model.Languages;
import br.com.cristiano.wakanda.model.Storage;
import br.com.cristiano.wakanda.view.BasicView;
import br.com.cristiano.wakanda.view.util.MessagesUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public abstract class BaseTest {

    protected final ByteArrayOutputStream out = new ByteArrayOutputStream();
    protected PrintStream originalOut;

    @BeforeClass
    public static void setupForClass() {
        MessagesUtils.load(Languages.EN);
    }

    protected void assertMessageIsPresent(String key) {
        assertTextIsPresent(MessagesUtils.getText(key));
    }

    protected void assertMessageIsPresent(String key, Object... params) {
        assertTextIsPresent(MessagesUtils.getText(key, params));
    }

    protected void assertTextIsPresent(String text) {
        assertTrue(this.out.toString().contains(text));
    }

    protected void deleteGames() {
        new Storage().deleteGames();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Before
    public void setUpBeforeTest() {
        originalOut = System.out;
        this.out.reset();
        System.setOut(new PrintStream(this.out));
    }

    protected void setUserInput(byte[] bytes) {
        BasicView.changeDefaultInput(new ByteArrayInputStream(bytes));
    }

}