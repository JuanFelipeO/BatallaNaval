package com.uamnizales.batallanaval.batallanaval.servicios;

import com.uamnizales.batallanaval.batallanaval.modelo.dto.RespuestaDTO;
import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Usuario;
import com.uamnizales.batallanaval.batallanaval.repositorio.UsuarioRepositorio;
import com.uamnizales.batallanaval.batallanaval.utilidades.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class UsuarioServicio {
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public ResponseEntity<Object> getAllUsuarios() {
        RespuestaDTO respuesta = new RespuestaDTO(Constants.SUCCESSFUL, usuarioRepositorio.findAll(), null);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    public ResponseEntity<Object> getUsuarioPorCodigo(String codigo) {
        if (usuarioRepositorio.existsById(codigo)) {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.SUCCESSFUL, usuarioRepositorio.findById(codigo).get(),
                            null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RespuestaDTO(Constants.DATA_NOT_FOUND, null,
                Constants.ERROR_DATA_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> guadarUsuario(Usuario usuario)
    {
        try
        {
            Usuario usuarioAlmacenado = usuarioRepositorio.save(usuario);
            return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL, usuarioAlmacenado,
                    null), HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE, null,
                    ex.getMessage()), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> borrarUsuarioPorId(String codigo)
    {
        if(usuarioRepositorio.existsById(codigo))
        {
            usuarioRepositorio.deleteById(codigo);
            return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,codigo,null),HttpStatus.OK);
        }
        return new ResponseEntity<>(new RespuestaDTO(Constants.DATA_NOT_FOUND, null,
                Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> actualizarUsuario(Usuario usuario)
    {
        if(usuarioRepositorio.existsById(String.valueOf(usuario.getId())))
        {
            try {
                Usuario usuarioAlmacenado = usuarioRepositorio.save(usuario);
                return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,usuarioAlmacenado,
                        null),HttpStatus.ACCEPTED);
            }
            catch (Exception ex)
            {
                return new ResponseEntity<>(new RespuestaDTO(Constants.ERROR_PERSISTENCE_SAVE, null,
                        ex.getMessage()),HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(new RespuestaDTO(Constants.DATA_NOT_FOUND, null,
                Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);

    }
}