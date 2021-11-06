package com.texoit.shared.exception;

import com.texoit.interval.IntervalContainer;
import com.texoit.shared.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class HandlerExceptionResponse {


    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<MyResponse> handleRuntimeException(RuntimeException exception) {
        log.error("handleRuntimeException", exception);
        return new ResponseEntity<>(new MyResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno, tente novamente em alguns instantes"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<MyResponse> handleNullPointerException(NullPointerException exception) {
        log.error("handleNullPointerException", exception);
        return new ResponseEntity<>(new MyResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno.Verifique se os dados estão de acordo com o layout definido"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
