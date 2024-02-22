package com.playtech.listo.seguridad.filtros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtech.listo.seguridad.jwt.JwtUtils;
import com.playtech.listo.servicio.detalles_usuario.DetallesUsuarioServicioImpl;
import com.playtech.listo.utils.MensajesErrorEnum;
import com.playtech.listo.utils.ResponseHandler;
import com.playtech.listo.utils.dto.ErrorDTO;
import com.playtech.listo.utils.dto.GenericDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final DetallesUsuarioServicioImpl detallesUsuarioServicio;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);

            if (jwtUtils.isTokenValid(token)) {
                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = detallesUsuarioServicio.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                try {
                    jwtUtils.getClaim(token, Claims::getExpiration);
                } catch (ExpiredJwtException e) {
                    responseClient(response,
                            ResponseEntity.ok().body(
                                    GenericDTO.error(
                                            MensajesErrorEnum.TOKEN_EXPIRO, HttpStatus.UNAUTHORIZED.value())));

                } catch (SignatureException | MalformedJwtException e) {
                    responseClient(response,
                            ResponseEntity.ok().body(
                                    GenericDTO.error(
                                            MensajesErrorEnum.JWT_INVALIDO, HttpStatus.UNAUTHORIZED.value())));
                }

                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void responseClient(final HttpServletResponse response, final ResponseEntity<Object> responseEntity) {
        try {
            response.setStatus(responseEntity.getStatusCode().value());
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
            response.getWriter().flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
