package com.uamnizales.batallanaval.batallanaval.modelo.dto;

import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Barcos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor

public class CasillaBarco implements Serializable
{
    private Barcos barcos;
    private boolean marcada;
}
