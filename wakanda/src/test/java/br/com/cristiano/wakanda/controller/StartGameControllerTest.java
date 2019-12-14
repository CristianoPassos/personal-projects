package br.com.cristiano.wakanda.controller;

import org.junit.Test;

import br.com.cristiano.wakanda.WakandaProject;
import br.com.cristiano.wakanda.factory.GameFactory;

public class StartGameControllerTest extends BaseControllerTest<StartGameController> {

	public StartGameControllerTest() {
		super(new StartGameController());
	}

	@Test
	public void startGameTest() {
		WakandaProject.setGameInPlay(GameFactory.createNewGame());
		controller.startGame();
		assertMessageIsPresent("game.goal");
	}

}
