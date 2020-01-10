package br.com.cristiano.wakanda.controller;

import br.com.cristiano.wakanda.BaseTest;

public abstract class BaseControllerTest<T extends BaseController<?>> extends BaseTest {

    protected final T controller;

    public BaseControllerTest(T controller) {
        super();
        this.controller = controller;
    }

}