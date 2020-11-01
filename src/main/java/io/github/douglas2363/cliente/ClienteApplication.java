package io.github.douglas2363.cliente;

import io.github.douglas2363.cliente.model.entity.Cliente;
import io.github.douglas2363.cliente.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClienteApplication extends SpringBootServletInitializer {

//        @Bean
//        public CommandLineRunner run(@Autowired ClienteRepository repository){
//            return  args -> {
//                Cliente cliente = Cliente.builder().cpf("02856643108").nome("Douglas Lima").build();
//                repository.save(cliente);
//            };
//        }

    public static void main(String[] args)  {
        SpringApplication.run(ClienteApplication.class, args);
    }
}
