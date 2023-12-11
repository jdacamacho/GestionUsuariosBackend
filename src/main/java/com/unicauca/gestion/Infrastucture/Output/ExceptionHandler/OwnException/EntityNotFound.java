package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException;

import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class EntityNotFound extends RuntimeException{
    private final String messageKey;
    private final String code;

    public EntityNotFound(ErrorCode code){
      super(code.getCode());
      this.messageKey = code.getMessageKey();
      this.code = code.getCode();
    }

    public EntityNotFound(final String message){
      super(message);
      this.messageKey = ErrorCode.ENTITY_NOT_FOUND.getCode();
      this.code = ErrorCode.ENTITY_NOT_FOUND.getCode();
    }
}
