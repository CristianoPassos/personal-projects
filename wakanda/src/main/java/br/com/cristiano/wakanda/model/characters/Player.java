package br.com.cristiano.wakanda.model.characters;

import br.com.cristiano.wakanda.view.util.MessagesUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player extends BaseCharacter implements Serializable {
    private static final long serialVersionUID = -7326505656159139166L;

    private int level;

    public Player(String name) {
        super(name, 2, 0, 100);
        this.level = 1;
    }

    public boolean addExperience(BaseCharacter enemy) {
        exp += (enemy.getExp() / level);
        boolean levelUp = exp >= 100;
        if (levelUp) {
            attack += (level * 1.2);
            level++;
            exp = 0;
            baseLife *= 1.1;
            restoreLife();
        }
        return levelUp;
    }

    @Override
    public List<String> describe() {
        List<String> describe = new ArrayList<>(super.describe());
        describe.add(MessagesUtils.getText("game.character.level", level));
        return describe;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return MessagesUtils.getText("game.setup.load.name") + " " + this.name + "  "
                + MessagesUtils.getText("game.setup.load.level") + " " + +this.level;
    }
}