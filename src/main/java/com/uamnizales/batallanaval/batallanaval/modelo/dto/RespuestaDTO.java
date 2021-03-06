package com.uamnizales.batallanaval.batallanaval.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RespuestaDTO implements Serializable {
    private String message;
    private Object data;
    private String error;

}
