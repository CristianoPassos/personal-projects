package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.BaronHelmutZemo;
import br.com.cristiano.wakanda.model.characters.BaronMacabre;

import java.util.Arrays;

public class BirninZana extends BaseCity {
    private static final long serialVersionUID = -2394805126222092949L;

    public BirninZana() {
        super("Birnin Zana", "Capital City", Arrays.asList(new BaronMacabre(), new BaronHelmutZemo()), Arrays.asList());
    }
}
