package com.uamnizales.batallanaval.batallanaval.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class Coordenada implements Serializable {
    private int filas;
    private int columnas;
}
