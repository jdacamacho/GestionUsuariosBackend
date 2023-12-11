package com.unicauca.gestion.Apliccation.Output;

public interface StudentFormatterIntPort {
    public void returnResponseErrorEntityExists(String message);
    public void returnResponseErrorEntityNotFound(String message);
    public void returnResponseBusinessRuleViolated(String message);
}
