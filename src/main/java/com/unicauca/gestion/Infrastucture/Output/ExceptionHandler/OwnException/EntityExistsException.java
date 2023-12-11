package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException;

import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class EntityExistsException extends RuntimeException{
    private final String messageKey;
    private final String code;

    public EntityExistsException(ErrorCode code){
      super(code.getCode());
      this.messageKey = code.getMessageKey();
      this.code = code.getCode();
    }

    public EntityExistsException(final String message){
      super(message);
      this.messageKey = ErrorCode.ENTITY_EXISTS.getCode();
      this.code = ErrorCode.ENTITY_EXISTS.getCode();
    }
}
