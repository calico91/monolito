package com.playtech.listo.repositorio;

import com.playtech.listo.modelo.Roles;
import com.playtech.listo.utils.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepositorio extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(ERole name);
}
