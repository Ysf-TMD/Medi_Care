package com.ysf.test.utils.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<String> errors ;
    public ValidationException( List<String>errors){
        super("Erreur de validation");
        this.errors = errors ;

    }

    public List<String>getErreurs(){
        return errors ;
    }

}
