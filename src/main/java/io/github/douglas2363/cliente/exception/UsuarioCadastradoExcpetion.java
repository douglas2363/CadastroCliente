package io.github.douglas2363.cliente.exception;

public class UsuarioCadastradoExcpetion extends RuntimeException{

    public UsuarioCadastradoExcpetion(String login) {

        super("Usuário ja cadastrado para o login :" + login);
    }
}