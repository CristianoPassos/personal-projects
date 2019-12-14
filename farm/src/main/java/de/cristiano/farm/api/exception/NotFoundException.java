package de.cristiano.farm.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource requested not found")
public class NotFoundException extends RuntimeException {

}
