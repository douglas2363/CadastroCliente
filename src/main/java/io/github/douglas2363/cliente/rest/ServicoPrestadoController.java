package io.github.douglas2363.cliente.rest;

import io.github.douglas2363.cliente.model.entity.ServicoPrestado;
import io.github.douglas2363.cliente.model.repository.ServicoPrestadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
@CrossOrigin("http://localhost:4200")
public class ServicoPrestadoController {


    private final ServicoPrestadoRepository servicoPrestadoRepository;

    @Autowired
    public ServicoPrestadoController(ServicoPrestadoRepository servicoPrestadoRepository) {

        this.servicoPrestadoRepository = servicoPrestadoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar (@RequestBody @Valid ServicoPrestado servicoPrestado){
        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> obterTodos(){
        return  servicoPrestadoRepository.findAll();
    }

    @GetMapping("{id}")
    public ServicoPrestado acharPorId(@PathVariable Integer id){
        return  servicoPrestadoRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não Encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deletar (@PathVariable Integer id){
        servicoPrestadoRepository
                .findById(id)
                .map(servicoPrestado -> {
                   servicoPrestadoRepository.delete(servicoPrestado);
                   return Void.TYPE;
        })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoPrestado servicoPrestadoAtualizado){
        servicoPrestadoRepository
                .findById(id)
                .map(servicoPrestado -> {
                    servicoPrestado.setDescricao(servicoPrestadoAtualizado.getDescricao());
                    servicoPrestado.setCliente(servicoPrestadoAtualizado.getCliente());
                    servicoPrestado.setValor(servicoPrestadoAtualizado.getValor());
                    return servicoPrestadoRepository.save(servicoPrestado);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));

    }

}
