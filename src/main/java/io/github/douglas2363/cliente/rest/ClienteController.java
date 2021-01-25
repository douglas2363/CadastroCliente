package io.github.douglas2363.cliente.rest;

import io.github.douglas2363.cliente.model.entity.Cliente;
import io.github.douglas2363.cliente.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
//@CrossOrigin("http://localhost:4200")
public class ClienteController {


    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> obterTodos(){
        return  clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar ( @RequestBody @Valid Cliente cliente){
        return clienteRepository.save(cliente);
    }


    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return  clienteRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
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
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
        clienteRepository
                .findById(id)
                .map(cliente -> {
                   cliente.setNome(clienteAtualizado.getNome());
                   cliente.setCpf(clienteAtualizado.getCpf());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

}
