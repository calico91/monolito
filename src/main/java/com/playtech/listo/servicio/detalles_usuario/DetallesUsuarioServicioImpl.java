package com.playtech.listo.servicio.detalles_usuario;

import com.playtech.listo.modelo.Usuario;
import com.playtech.listo.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DetallesUsuarioServicioImpl implements UserDetailsService {
    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        Collection<? extends GrantedAuthority> authorities = usuario.getSubModulos()
                .stream()
                .map(modulo -> new SimpleGrantedAuthority(modulo.getDescripcion()) )
                .collect(Collectors.toSet());

        return new User(usuario.getLogin(),
                usuario.getClave(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
