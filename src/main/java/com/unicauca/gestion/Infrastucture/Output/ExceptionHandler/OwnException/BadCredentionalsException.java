package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException;

import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class BadCredentionalsException extends RuntimeException{
    private final String messageKey;
    private final String code;

    public BadCredentionalsException(ErrorCode code){
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public BadCredentionalsException(final String message){
        super(message);
        this.messageKey = ErrorCode.BAD_CREDENTIALS.getCode();
        this.code = ErrorCode.BAD_CREDENTIALS.getCode();
    }
}

