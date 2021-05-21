package com.uamnizales.batallanaval.batallanaval.controlador;

import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Usuario;
import com.uamnizales.batallanaval.batallanaval.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario")
@Validated

public class UsuarioControlador
{
    private UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioControlador (UsuarioServicio usuarioServicio)
    {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Object> getAllUsuarios()
    {
        return usuarioServicio.getAllUsuarios();
    }

    @GetMapping(path = "{/codigo}")
    public @ResponseBody ResponseEntity<Object> getUsuarioPorId(@PathVariable("codigo") String codigo)
    {
        return usuarioServicio.borrarUsuarioPorId(codigo);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> guardarUsuario(@RequestBody Usuario usuario)
    {
        return usuarioServicio.guadarUsuario(usuario);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<Object> actualizaUsuario(@RequestBody Usuario usuario)
    {
        return usuarioServicio.actualizarUsuario(usuario);
    }

    @DeleteMapping(path="/{codigo}")
    public @ResponseBody ResponseEntity<Object> borrarUsuario(@PathVariable("codigo") String codigo)
    {
        return usuarioServicio.borrarUsuarioPorId(codigo);
    }
}
