package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.Akhenaten;
import br.com.cristiano.wakanda.model.characters.Alkhema;

import java.util.Arrays;

public class BirninAzzaria extends BaseCity {

    private static final long serialVersionUID = 408533625039036568L;

    public BirninAzzaria() {
        super("Birnin Azzaria", "City", Arrays.asList(new Akhenaten(), new Alkhema()), null);
        this.nearByCities = Arrays.asList(new BirninTChaka(this), new BirninSyan());
    }

    @Override
    public String getCityName() {
        return this.cityName;
    }

    @Override
    public String getCityType() {
        return this.cityType;
    }
}
