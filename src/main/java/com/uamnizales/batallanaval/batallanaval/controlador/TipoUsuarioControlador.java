package com.uamnizales.batallanaval.batallanaval.controlador;

import com.uamnizales.batallanaval.batallanaval.modelo.entidades.TipoUsurio;
import com.uamnizales.batallanaval.batallanaval.servicios.TipoUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tipousuario")
@Validated

public class TipoUsuarioControlador
{
    private TipoUsuarioServicio tipoUsuarioServicio;

    @Autowired
    public TipoUsuarioControlador(TipoUsuarioServicio tipoUsuarioServicio)
    {
        this.tipoUsuarioServicio = tipoUsuarioServicio;
    }

    @GetMapping
    public Iterable<TipoUsurio> getAllTipoUsuarios()
    {

        return tipoUsuarioServicio.getAllTipoUsuarios();
    }

    @PostMapping
    public TipoUsurio createTipoUsuario(@RequestBody TipoUsurio tipoUsurio)
    {
        return tipoUsuarioServicio.createTipoUsuario(tipoUsurio);
    }
}
