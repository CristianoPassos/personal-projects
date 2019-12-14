package br.com.cristiano.wakanda.controller;

import org.junit.Test;

public class ChooseLanguageControllerTest extends BaseControllerTest<ChooseLanguageController> {

	public ChooseLanguageControllerTest() {
		super(new ChooseLanguageController());
	}

	@Test
	public void selectLanguageEnglishTest() {
		setUserInput("0".getBytes());
		controller.chooseLanguage();
		assertTextIsPresent("English selected");
	}

	@Test
	public void selectLanguageWrongTest() {
		setUserInput("as12".getBytes());
		controller.chooseLanguage();
		assertTextIsPresent("English selected");
	}

}
