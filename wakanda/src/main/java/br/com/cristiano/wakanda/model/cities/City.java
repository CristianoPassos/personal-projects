package br.com.cristiano.wakanda.model.cities;

import java.io.Serializable;
import java.util.List;

import br.com.cristiano.wakanda.model.characters.BaseCharacter;

public interface City extends Serializable {

	public String getCityName();

	public String getCityType();

	public BaseCharacter getEnemy();

	public int[] getNearByCitiesIndexes();

	public List<String> listNearByCities();

	public City recoverNearCityByIndex(int id);
}
