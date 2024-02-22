package com.playtech.listo.modelo;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubModulo {
    @Id
    Integer codigoSubModulo;
    @Column(length = 80)
    String descripcion;
    @Column(length = 1)
    String estado;

}
