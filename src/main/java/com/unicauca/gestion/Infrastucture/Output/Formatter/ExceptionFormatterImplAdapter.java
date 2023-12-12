package com.unicauca.gestion.Infrastucture.Output.Formatter;

import org.springframework.stereotype.Service;
import com.unicauca.gestion.Apliccation.Output.ExceptionFormatterIntPort;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException.BussinesRuleException;
import com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.OwnException.EntityExistsException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExceptionFormatterImplAdapter implements ExceptionFormatterIntPort{

    @Override
    public void returnResponseErrorEntityExists(String message) {
        EntityExistsException objException = new EntityExistsException(message);
        throw objException;
    }

    @Override
    public void returnResponseErrorEntityNotFound(String message) {
        EntityNotFoundException objException = new EntityNotFoundException(message);
        throw objException;
    }

    @Override
    public void returnResponseBusinessRuleViolated(String message) {
        BussinesRuleException objException = new BussinesRuleException(message);
        throw objException;
    }
    
}
