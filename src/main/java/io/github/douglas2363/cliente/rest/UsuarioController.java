package io.github.douglas2363.cliente.rest;

import io.github.douglas2363.cliente.exception.UsuarioCadastradoExcpetion;
import io.github.douglas2363.cliente.model.entity.Usuario;
import io.github.douglas2363.cliente.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {


    private final  UsuarioService usuarioService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
       try {
           usuarioService.salvar(usuario);
       }catch (UsuarioCadastradoExcpetion e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage() );
       }


    }
}
