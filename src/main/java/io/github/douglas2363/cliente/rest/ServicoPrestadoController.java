package io.github.douglas2363.cliente.rest;

import io.github.douglas2363.cliente.Util.BigDecimalConverter;
import io.github.douglas2363.cliente.model.entity.Cliente;
import io.github.douglas2363.cliente.model.entity.ServicoPrestado;
import io.github.douglas2363.cliente.model.repository.ClienteRepository;
import io.github.douglas2363.cliente.model.repository.ServicoPrestadoRepository;
import io.github.douglas2363.cliente.rest.dto.ServicoPrestadoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class ServicoPrestadoController {


    private final ServicoPrestadoRepository servicoPrestadoRepository;

    private final ClienteRepository clienteRepository;

    private final BigDecimalConverter bigDecimalConverter;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  ServicoPrestado salvar (@RequestBody ServicoPrestadoDTO dto){

        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente inexistente"));


        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData( data );
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);

    }
}
