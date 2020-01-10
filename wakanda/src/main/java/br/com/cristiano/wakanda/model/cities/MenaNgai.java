package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.Arcadius;
import br.com.cristiano.wakanda.model.characters.ArianaMcKenzie;

import java.util.Arrays;

public class MenaNgai extends BaseCity {
    private static final long serialVersionUID = 8404621584730655273L;

    public MenaNgai() {
        super("Mena Ngai", "Great Mound", Arrays.asList(new Arcadius(), new ArianaMcKenzie()),
                Arrays.asList(new Necropolis()));
    }
}
