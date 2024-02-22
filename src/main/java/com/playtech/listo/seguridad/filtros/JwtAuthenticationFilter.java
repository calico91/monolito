package com.playtech.listo.seguridad.filtros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtech.listo.controlador.login.dto.LoginRespuestaDTO;
import com.playtech.listo.mapper.UsuarioMapper;
import com.playtech.listo.modelo.Usuario;
import com.playtech.listo.repositorio.UsuarioRepositorio;
import com.playtech.listo.seguridad.jwt.JwtUtils;
import com.playtech.listo.utils.MensajesErrorEnum;
import com.playtech.listo.utils.dto.ErrorDTO;
import com.playtech.listo.utils.dto.GenericDTO;
import com.playtech.listo.utils.ResponseHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * clase que filtra si el usuario intenta loguearse, si es correcto o incorrecto
 */

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    private final UsuarioRepositorio usuarioRepositorio;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        Usuario usuario;
        String username;
        String password;

        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            username = usuario.getLogin();
            password = usuario.getClave();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        String token = jwtUtils.generateAccesToken(user.getUsername());

        response.addHeader("Authorization", token);

        Usuario usuario = usuarioRepositorio.findByLogin(user.getUsername()).orElse(new Usuario());
        LoginRespuestaDTO loginRespuestaDTO = UsuarioMapper.MAPPER.toLoginRespuestaDTO(usuario);
        loginRespuestaDTO.setToken(token);

        logger.info("login correcto: " + loginRespuestaDTO);

        response.getWriter().write(new ObjectMapper().writeValueAsString(
                GenericDTO.correcto(loginRespuestaDTO)));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                              final AuthenticationException failed) throws IOException {

        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(
                GenericDTO.error(MensajesErrorEnum.LOGIN_INCORRRECTO, HttpStatus.UNAUTHORIZED.value())));

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
    }
}
