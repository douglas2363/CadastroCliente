package io.github.douglas2363.cliente.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> errors;

    public ApiErros(List<String> errors){
        this.errors = errors;
    }

   public ApiErros(String messages){
        this.errors = Arrays.asList(messages);
   }
}
