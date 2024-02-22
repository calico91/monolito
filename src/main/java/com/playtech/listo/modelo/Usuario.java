package com.playtech.listo.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@Builder
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer codigoUsuario;
    @Column(length = 80)
    String nombre;
    @Column(length = 80)
    String apellido;
    //Ciudad codigoCiudad;
    @Column(length = 50, unique = true)
    String login;
    String clave;
    @Column(length = 1)
    String estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rutas",
            joinColumns = @JoinColumn(
                    name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "ruta_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "ruta_id"})})
    Set<Ruta> rutas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_modulo",
            joinColumns = @JoinColumn(name = "codigo_usuario"),
            inverseJoinColumns = @JoinColumn(name = "codigo_modulo"))
    Set<SubModulo> subModulos = new HashSet<>();

}
