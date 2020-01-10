package br.com.cristiano.wakanda.model;

import br.com.cristiano.wakanda.model.characters.Player;
import br.com.cristiano.wakanda.model.cities.BirninDjata;
import br.com.cristiano.wakanda.model.cities.City;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Serializable {

    private static final long serialVersionUID = -1459703123591010134L;
    private final Integer id;
    private City currentCity;
    private Player player;

    public Game(String playerName) {
        super();
        this.id = ThreadLocalRandom.current().nextInt();
        this.currentCity = new BirninDjata();
        this.player = new Player(playerName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            Game other = (Game) obj;
            return this.id.equals(other.id);
        }
        return false;
    }

    public City getCurrentCity() {
        return this.currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public Integer getId() {
        return this.id;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return player.getName();
    }
}
