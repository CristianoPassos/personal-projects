package br.com.cristiano.wakanda.controller.commands;

import org.junit.Test;

import br.com.cristiano.wakanda.controller.commands.executors.SaveCommand;
import br.com.cristiano.wakanda.factory.GameFactory;

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
