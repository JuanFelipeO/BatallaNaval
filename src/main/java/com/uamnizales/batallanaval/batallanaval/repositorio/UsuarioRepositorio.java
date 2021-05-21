package com.uamnizales.batallanaval.batallanaval.repositorio;

import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, String>
{
}
