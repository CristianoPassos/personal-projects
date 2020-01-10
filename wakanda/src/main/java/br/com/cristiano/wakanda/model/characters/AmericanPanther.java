package br.com.cristiano.wakanda.model.characters;

import java.io.Serializable;

public class AmericanPanther extends BaseCharacter implements Serializable {
    private static final long serialVersionUID = -7326505656159139166L;

    public AmericanPanther() {
        super("American Panther", 20, 30, 60);
    }

}