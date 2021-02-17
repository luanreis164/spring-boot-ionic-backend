package com.luanreis.cursosb.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(){
        super ("Não encontrado");
    }

}
