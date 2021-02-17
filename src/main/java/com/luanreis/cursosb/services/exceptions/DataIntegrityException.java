package com.luanreis.cursosb.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(){
        super ("Não é possivel excluir uma categoria que possui produtos.");
    }

}
