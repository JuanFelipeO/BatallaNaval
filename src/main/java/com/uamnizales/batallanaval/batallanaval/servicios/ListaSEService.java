package com.uamnizales.batallanaval.batallanaval.servicios;

import com.uamnizales.batallanaval.batallanaval.modelo.ListaSE;
import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Usuario;
import com.uamnizales.batallanaval.batallanaval.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class ListaSEService {
    private ListaSE listaSE;

    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public ListaSEService(UsuarioRepositorio usuarioRepositorio)
    {
        this.usuarioRepositorio = usuarioRepositorio;
        this.listaSE = new ListaSE();
    }

    @PostConstruct
    public void cargar()
    {
        Iterable<Usuario> usuarios = usuarioRepositorio.findAll();
        for(Usuario usuario:usuarios)
        {
            this.listaSE.adicionarNodo(usuario);
        }
    }

    public int contarNodos()
    {
        return listaSE.getCont();
    }

    public String listarNodos()
    {
        return listaSE.listadoNodos();
    }

    public boolean adicionarNodo(Object dato)
    {
        this.listaSE.adicionarNodo(dato);
        if(dato instanceof Usuario)
        {
            this.usuarioRepositorio.save((Usuario) dato);
        }
        return true;
    }

    public Usuario encontrarBarcoPorCodigo(String codigo)
    {
        return (Usuario) this.listaSE.encontrarDatoxCodigo(codigo);
    }
}
