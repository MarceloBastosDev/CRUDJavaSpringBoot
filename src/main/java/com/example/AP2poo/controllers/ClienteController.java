package com.example.AP2poo.controllers;


import com.example.AP2poo.Service.ClienteService;
import com.example.AP2poo.model.Cliente;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //indica ao Spring que essa é uma classe Controller
@RequestMapping("/clientes") //indica o endpoint que o Controller responde

public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping //mapeia as requisições HTTP GET com base na URL /clientes
    public List<Cliente> listarClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}") //utilizando PathVariable extrai o valor da URL e passa como argumento para o metodo
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable int id){
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(params = "idade") // responde a requisições get que incluem o parametro idade
    public List<Cliente> filtrarClientesPorIdade(@RequestParam int idade){ //RequestParm extrai o valor do paramtro da requisição
        return clienteService.getClientesByIdade(idade);
    }

    @PostMapping // mapeia requisições HTTP POST
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente){ //RequestBody converte a requisição JSON no Body em um objeto Cliente e passado comom argumento
        Cliente clienteCriado = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @PutMapping("/{id}") //mapeia requisições HTTP POST
    public String atualizarClientePeloId(@PathVariable int id, @RequestBody Cliente cliente) { //PathVariable extrai o valor id da URL e passa como argumento e RequestBody converte o body para um objeto Cliente
        boolean Atualizado = clienteService.updateCliente(id, cliente);
        if (Atualizado){
            return "Cliente atualizado!";
        }
        return "Cliente não encontrado!";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable int id){
        boolean Removido = clienteService.deleteCliente(id);
        if (Removido){
            return ResponseEntity.ok("Cliente removido com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
    }


}
