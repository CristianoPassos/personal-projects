package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.*;

import java.util.Arrays;

public class BirninTChaka extends BaseCity {
    private static final long serialVersionUID = 5907046150696811786L;

    public BirninTChaka(BirninAzzaria city) {
        super("Birnin T'Chaka", "City",
                Arrays.asList(new Aquarius(), new Achebe(), new Aries(), new Raymond(), new AmericanPanther()),
                Arrays.asList(city));
    }

}
