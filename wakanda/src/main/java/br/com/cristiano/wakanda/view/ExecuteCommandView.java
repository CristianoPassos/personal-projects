package br.com.cristiano.wakanda.view;

import br.com.cristiano.wakanda.controller.commands.Command;
import br.com.cristiano.wakanda.controller.commands.Commands;

import java.util.Optional;

public class ExecuteCommandView extends BasicView {

    public Command readCommand() {
        int idCommand = readIntUserInput(Optional.empty(), Commands.getIds());
        return Commands.getExecuter(idCommand).get();
    }
}
