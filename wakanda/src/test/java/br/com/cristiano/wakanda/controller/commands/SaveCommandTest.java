package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.controller.commands.executors.SaveCommand;
import br.com.cristiano.wakanda.factory.GameFactory;
import org.junit.Test;

public class SaveCommandTest extends BaseCommandTest {
    public SaveCommandTest() {
        super(new SaveCommand());
    }

    @Test
    public void listNearByCitiesTest() {
        this.command.execute(GameFactory.createNewGame(), view);
        assertMessageIsPresent("service.storage.save.success");
    }

}
