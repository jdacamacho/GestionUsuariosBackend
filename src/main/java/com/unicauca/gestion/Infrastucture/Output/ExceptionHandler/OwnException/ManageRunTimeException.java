package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException;

import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class ManageRunTimeException  extends RuntimeException{
    protected ErrorCode errorCode;
    
    public abstract String formatException();
}
