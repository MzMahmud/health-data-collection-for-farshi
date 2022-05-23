package com.moazmahmud.spring_boot_thymeleaf.common;

import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.BadRequestException;
import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.NotFoundException;
import com.moazmahmud.spring_boot_thymeleaf.common.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
public abstract class BaseRestController {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<RestResponse> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(RestResponse.builder()
                                  .success(false)
                                  .error(exception.getMessage())
                                  .build());
    }

    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<RestResponse> handleNotFoundException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RestResponse.builder()
                                  .success(false)
                                  .error(exception.getMessage())
                                  .build());
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<RestResponse> handleUnauthorizedException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(RestResponse.builder()
                                  .success(false)
                                  .error(exception.getMessage())
                                  .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<RestResponse> handleInternalServerException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.builder()
                                  .success(false)
                                  .error(exception.getMessage())
                                  .build());
    }
}
