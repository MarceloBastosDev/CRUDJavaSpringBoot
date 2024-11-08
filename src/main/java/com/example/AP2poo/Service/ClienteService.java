package com.example.AP2poo.Service;

import com.example.AP2poo.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //indica que é uma classe Service
public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>(); //criando um arraylist para Clientes
    private int nextId = 1;

    // GET -> Retornar os clientes da lista

    public List<Cliente> getAllClientes(){ //metodo que retorna a lista de Clientes
        return clientes;
    }

    public Cliente getClienteById(int id){ //retorna um cliente com o id fornecido
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null); //utiliza uma expressão lambda como condição para o filtro
    }

    public List<Cliente>  getClientesByIdade(int idade){ //retorna uma lista com todos os clientes com a idade fornecida
        return clientes.stream().filter(c -> c.getIdade() == idade).collect(Collectors.toList()); //collect coleta o resultado do filtro e converte em uma lista
    }

    // POST -> Inserir cliente na lista

    public Cliente createCliente(Cliente cliente){
        cliente.setId(nextId++);
        clientes.add(cliente);
        return cliente;
    }

    // PUT -> Atualizar um cliente na lista pelo ID


    public boolean updateCliente(int id, Cliente clienteAtualizado){
        for (int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getId() == id){
                clientes.get(i).setNome(clienteAtualizado.getNome());
                clientes.get(i).setIdade((clienteAtualizado.getIdade()));
                clientes.get(i).setProfissao(clienteAtualizado.getProfissao());
                return true;
            }
        }
        return false;
    }

    // DELETE -> remove o cliente pelo id

    public boolean deleteCliente(int id){
        for(int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getId() == id){
                clientes.remove((i));
                return true;
            }
        }
        return false;
    }
}
