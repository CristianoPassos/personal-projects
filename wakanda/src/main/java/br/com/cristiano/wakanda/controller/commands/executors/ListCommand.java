package br.com.cristiano.wakanda.controller.commands.executors;

import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.model.Game;
import br.com.cristiano.wakanda.view.BasicView;
import br.com.cristiano.wakanda.view.util.MessagesUtils;

public class ListCommand implements Command {

    @Override
    public void execute(Game game, BasicView view) {
        view.printMessage("game.command.list.commands");
        view.printText(MessagesUtils.getAllByPrefix("game.command.list.commands.describe"));
    }

}
