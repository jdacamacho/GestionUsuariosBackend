package com.unicauca.gestion.Apliccation.Input;

import com.unicauca.gestion.Domain.Models.Credentionals;
import com.unicauca.gestion.Domain.Models.Login;

public interface ManageAuthCUIntPort {
    public Credentionals login(Login login);
}
