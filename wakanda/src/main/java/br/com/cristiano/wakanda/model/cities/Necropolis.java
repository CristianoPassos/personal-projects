package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.Aries;
import br.com.cristiano.wakanda.model.characters.Raymond;

import java.util.Arrays;

public class Necropolis extends BaseCity {
    private static final long serialVersionUID = -1800190976833521248L;

    public Necropolis() {
        super("Necropolis", "City of the Dead", Arrays.asList(new Aries(), new Raymond()),
                Arrays.asList(new BirninZana()));
    }
}
