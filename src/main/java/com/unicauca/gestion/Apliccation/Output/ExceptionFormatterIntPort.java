package com.unicauca.gestion.Apliccation.Output;

public interface ExceptionFormatterIntPort {
    public void returnResponseErrorEntityExists(String message);
    public void returnResponseErrorEntityNotFound(String message);
    public void returnResponseBusinessRuleViolated(String message);
}
