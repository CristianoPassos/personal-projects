package br.com.cristiano.wakanda.model.cities;

import java.util.Arrays;

import br.com.cristiano.wakanda.model.characters.AndreasdeRuyter;
import br.com.cristiano.wakanda.model.characters.Aquarius;

public class BirninBashenga extends BaseCity {
	private static final long serialVersionUID = -2522922573212383167L;

	public BirninBashenga() {
		super("Birnin Bashenga", "City", Arrays.asList(new Aquarius(), new AndreasdeRuyter()),
				Arrays.asList(new MenaNgai()));
	}

}
