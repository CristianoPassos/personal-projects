package br.com.cristiano.wakanda.controller;

import br.com.cristiano.wakanda.WakandaProject;
import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.view.ExecuteCommandView;

public class RunCommandController extends BaseController<ExecuteCommandView> {

    public RunCommandController() {
        super(new ExecuteCommandView());
    }

    public void executePlayerCommand() {
        view.printMessage("game.command.ask.user.input");
        Command command = view.readCommand();
        view.clear();
        command.execute(WakandaProject.getGameInPlay(), view);
    }

}
