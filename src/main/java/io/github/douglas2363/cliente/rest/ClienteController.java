package io.github.douglas2363.cliente.rest;

import io.github.douglas2363.cliente.model.entity.Cliente;
import io.github.douglas2363.cliente.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar ( @RequestBody @Valid Cliente cliente){
        return clienteRepository.save(cliente);
    }


    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return  clienteRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deletar (@PathVariable Integer id){
        clienteRepository
                .findById(id)
                .map(cliente -> {
                   clienteRepository.delete(cliente);
                   return Void.TYPE;
        })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    //clienteAtualizado.setId(clienteAtualizado.getId());
                   cliente.setNome(clienteAtualizado.getNome());
                   cliente.setCpf(clienteAtualizado.getCpf());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

}
