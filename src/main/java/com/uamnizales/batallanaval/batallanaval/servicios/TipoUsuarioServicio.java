package com.uamnizales.batallanaval.batallanaval.servicios;

import com.uamnizales.batallanaval.batallanaval.modelo.entidades.TipoUsurio;
import com.uamnizales.batallanaval.batallanaval.repositorio.TipoUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TipoUsuarioServicio
{
    private TipoUsuarioRepositorio tipoUsuarioRepositorio;

    @Autowired
    public TipoUsuarioServicio(TipoUsuarioRepositorio tipoUsuarioRepositorio)
    {
        this.tipoUsuarioRepositorio = tipoUsuarioRepositorio;
    }

    public Iterable<TipoUsurio> getAllTipoUsuarios()
    {
        return tipoUsuarioRepositorio.findAll();
    }

    public TipoUsurio createTipoUsuario(TipoUsurio tipoUsurio)
    {
        return tipoUsuarioRepositorio.save(tipoUsurio);
    }
}
