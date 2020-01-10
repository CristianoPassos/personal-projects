package br.com.cristiano.wakanda.controller.commands.executors;

import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.controller.commands.Commands;
import br.com.cristiano.wakanda.model.ASCIIArt;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.model.characters.BaseCharacter;
import br.com.cristiano.wakanda.model.characters.Player;
import br.com.cristiano.wakanda.view.BasicView;

import java.util.Random;

public class ProtectCityCommand implements Command {
    private Random randon = new Random();

    private void attack(BaseCharacter attacker, BaseCharacter defender, BasicView view) {
        boolean attackBlocked = randon.nextBoolean();
        if (attackBlocked) {
            view.printMessage("game.command.protect.city.attack.blocked", attacker.getName());
        } else {
            defender.attacked(attacker);
            view.printMessage("game.command.protect.city.attack.hit", defender.getName(), attacker.getAttack(),
                    defender.getLife());
        }
    }

    private void computeExperience(Player player, BaseCharacter enemy, BasicView view) {
        boolean levelUp = player.addExperience(enemy);
        if (levelUp) {
            view.printText(new ASCIIArt().createArt("game.level.up"));
            view.printText(player.describe());
        } else {
            view.printMessage("game.command.protect.city.win", enemy.getExp(), player.getExp(), player.getLife());
        }
    }

    @Override
    public void execute(Game game, BasicView view) {
        BaseCharacter enemy = game.getCurrentCity().getEnemy();
        Player player = game.getPlayer();
        view.printMessage("game.command.protect.city");
        view.printText(enemy.describe());
        view.printMessage("game.command.protect.start");
        BaseCharacter winner = fight(player, enemy, view);
        view.printMessage("game.command.protect.end");
        if (winner instanceof Player) {
            computeExperience(player, enemy, view);
        } else {
            exitGame(game, view);
        }
        enemy.restoreLife();
    }

    private void exitGame(Game game, BasicView view) {
        view.printMessage("game.command.protect.city.exit");
        Commands.EXIT.getExecuter().execute(game, view);
    }

    private BaseCharacter fight(BaseCharacter player, BaseCharacter enemy, BasicView view) {
        while (enemy.isAlive() && player.isAlive()) {
            attack(player, enemy, view);
            attack(enemy, player, view);
        }
        return player.isAlive() == true ? player : enemy;
    }

}
