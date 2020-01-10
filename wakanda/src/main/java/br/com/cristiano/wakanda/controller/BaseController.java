package br.com.cristiano.wakanda.controller;

import br.com.cristiano.wakanda.view.BasicView;

public abstract class BaseController<T extends BasicView> {

    protected T view;

    public BaseController(T view) {
        super();
        this.view = view;
    }

}
