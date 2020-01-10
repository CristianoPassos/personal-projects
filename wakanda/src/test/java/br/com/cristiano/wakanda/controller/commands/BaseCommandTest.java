package br.com.cristiano.wakanda.controller.commands;

import br.com.cristiano.wakanda.BaseTest;
import br.com.cristiano.wakanda.view.BasicView;

public abstract class BaseCommandTest extends BaseTest {

    protected Command command;
    protected BasicView view = new BasicView();

    public BaseCommandTest(Command command) {
        super();
        this.command = command;
    }

}