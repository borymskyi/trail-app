package com.borymskyi.trail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dmitrii Borymskyi
 * @version 1.0
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProfileAlreadyExists extends RuntimeException {
}
