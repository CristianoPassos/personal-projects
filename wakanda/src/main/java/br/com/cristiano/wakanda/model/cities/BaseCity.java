package br.com.cristiano.wakanda.model.cities;

import br.com.cristiano.wakanda.model.characters.BaseCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseCity implements City {

    private static final long serialVersionUID = 5375202366367919309L;
    protected String cityName;
    protected String cityType;
    protected List<BaseCharacter> enemies;
    protected List<City> nearByCities;
    private Random random = new Random();

    public BaseCity(String cityName, String cityType, List<BaseCharacter> enemies, List<City> nearByCities) {
        super();
        this.cityName = cityName;
        this.cityType = cityType;
        this.enemies = enemies;
        this.nearByCities = nearByCities;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 instanceof BaseCity) {
            BaseCity otherPhase = (BaseCity) arg0;
            return getCityName().equals(otherPhase.getCityName());
        }
        return false;
    }

    @Override
    public String getCityName() {
        return this.cityName;
    }

    @Override
    public String getCityType() {
        return this.cityType;
    }

    @Override
    public BaseCharacter getEnemy() {
        int randon = random.nextInt(enemies.size());
        return enemies.get(randon);
    }

    @Override
    public int[] getNearByCitiesIndexes() {
        int[] indexes = new int[nearByCities.size()];
        for (int i = 0; i < nearByCities.size(); i++) {
            indexes[i] = i;
        }
        return indexes;
    }

    @Override
    public int hashCode() {
        return getCityName().hashCode();
    }

    @Override
    public List<String> listNearByCities() {
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < nearByCities.size(); i++) {
            texts.add(nearByCities.get(i) + "  [" + i + "]");
        }
        return texts;
    }

    @Override
    public City recoverNearCityByIndex(int index) {
        return nearByCities.get(index);
    }

    @Override
    public String toString() {
        return getCityName() + " - " + getCityType();
    }
}
