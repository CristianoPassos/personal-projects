package br.com.cristiano.wakanda.controller;

import org.junit.Test;

public class RunCommandControllerTest extends BaseControllerTest<RunCommandController> {
	public RunCommandControllerTest() {
		super(new RunCommandController());
	}

	@Test
	public void listTest() {
		setUserInput("98".getBytes());
		controller.executePlayerCommand();
		assertMessageIsPresent("game.command.list.commands.describe.move");
		assertMessageIsPresent("game.command.list.commands.describe.save");
	}

}
