package br.cristiano.eso.error;

import lombok.extern.java.Log;

import static java.util.logging.Level.SEVERE;

@Log
public class RequiredParamMissing extends RuntimeException {

    public RequiredParamMissing() {
    }

    public RequiredParamMissing(final String message) {
        super(message);
        log.log(SEVERE, message);
    }

    public RequiredParamMissing(final String message, final Throwable cause) {
        super(message, cause);
        log.log(SEVERE, message);
    }

}
