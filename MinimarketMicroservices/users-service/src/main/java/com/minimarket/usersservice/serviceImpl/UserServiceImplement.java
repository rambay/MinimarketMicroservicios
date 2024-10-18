package com.minimarket.usersservice.serviceImpl;

import com.minimarket.usersservice.model.Usuario;
import com.minimarket.usersservice.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplement implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findOneByEmail(email);

        if (usuarioOpt.isPresent()) {
            return new UserDetailImplement(usuarioOpt.get());
        } else {
            throw new UsernameNotFoundException("El usuario con dicho email: " + email + " no existe.");
        }
    }
}
