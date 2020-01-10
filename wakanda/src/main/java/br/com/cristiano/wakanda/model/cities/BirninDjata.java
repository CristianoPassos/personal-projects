package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.AbnerLittle;
import br.com.cristiano.wakanda.model.characters.Achebe;

import java.util.Arrays;

public class BirninDjata extends BaseCity {
    private static final long serialVersionUID = 8062259404504159927L;

    public BirninDjata() {
        super("Birnin Djata", "City", Arrays.asList(new AbnerLittle(), new Achebe()),
                Arrays.asList(new BirninAzzaria()));
    }
}
