package com.playtech.listo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer codigoRuta;
    @Column(length = 80)
    String descripcion;

    @Column(length = 20)
    String celular;

    @Column(length = 1)
    String estado;
}
