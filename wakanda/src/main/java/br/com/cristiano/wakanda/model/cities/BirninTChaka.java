package br.com.cristiano.wakanda.model.cities;

import java.util.Arrays;

import br.com.cristiano.wakanda.model.characters.Achebe;
import br.com.cristiano.wakanda.model.characters.AmericanPanther;
import br.com.cristiano.wakanda.model.characters.Aquarius;
import br.com.cristiano.wakanda.model.characters.Aries;
import br.com.cristiano.wakanda.model.characters.Raymond;

public class BirninTChaka extends BaseCity {
	private static final long serialVersionUID = 5907046150696811786L;

	public BirninTChaka(BirninAzzaria city) {
		super("Birnin T'Chaka", "City",
				Arrays.asList(new Aquarius(), new Achebe(), new Aries(), new Raymond(), new AmericanPanther()),
				Arrays.asList(city));
	}

}
