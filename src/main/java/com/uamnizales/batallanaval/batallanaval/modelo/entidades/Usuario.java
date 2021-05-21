package com.uamnizales.batallanaval.batallanaval.modelo.entidades;

import javax.persistence.*;

@Entity
public class Usuario extends Barcos {
    private short id;
    private String correo;
    private String contrasenia;
    private TipoUsurio tipoUsurioByTipoUsuario;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "contrasenia")
    public String getContrasenia()
    {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != usuario.id) return false;
        if (correo != null ? !correo.equals(usuario.correo) : usuario.correo != null) return false;
        if (contrasenia != null ? !contrasenia.equals(usuario.contrasenia) : usuario.contrasenia != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) id;
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (contrasenia != null ? contrasenia.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "codigo", nullable = false)
    public TipoUsurio getTipoUsurioByTipoUsuario()
    {
        return tipoUsurioByTipoUsuario;
    }

    public void setTipoUsurioByTipoUsuario(TipoUsurio tipoUsurioByTipoUsuario)
    {
        this.tipoUsurioByTipoUsuario = tipoUsurioByTipoUsuario;
    }
}
