package br.com.cristiano.wakanda.controller;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import br.com.cristiano.wakanda.model.Languages;
import br.com.cristiano.wakanda.view.ChooseLanguageView;
import br.com.cristiano.wakanda.view.util.MessagesUtils;

public class ChooseLanguageController extends BaseController<ChooseLanguageView> {

	public ChooseLanguageController() {
		super(new ChooseLanguageView());
	}

	private void changeOutputEncoding(ChooseLanguageView view) {
		try {
			PrintStream out = new PrintStream(System.out, true, "UTF-8");
			System.setOut(out);
		} catch (UnsupportedEncodingException e) {
			view.printText("UTF-8 Encoding not supported, special characters will not work");
		}
	}

	public void chooseLanguage() {
		changeOutputEncoding(view);
		view.printText("Please choose the Game Language:");
		Arrays.stream(Languages.values()).forEach(l -> view.printText(l.toString()));
		MessagesUtils.load(view.readLanguage());
		view.clear();
		view.printMessage("game.setup.language");
	}

}