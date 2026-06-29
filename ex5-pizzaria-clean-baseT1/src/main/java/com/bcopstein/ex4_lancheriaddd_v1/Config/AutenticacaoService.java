package com.bcopstein.ex4_lancheriaddd_v1.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Dados.ClienteSpringDataRepository;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Cliente;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final ClienteSpringDataRepository clienteRepository;

    @Autowired
    public AutenticacaoService(ClienteSpringDataRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        String role = cliente.getEmail().equalsIgnoreCase("admin@pizzaria.com") ? "ADMIN" : "USER";

        return User.builder()
                .username(cliente.getEmail())
                .password(cliente.getSenha()) 
                .roles(role)
                .build();
    }
}