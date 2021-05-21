package com.uamnizales.batallanaval.batallanaval.modelo.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Barcos
{
    private short id;
    private String tipoBarco;
    private short tamanio;

    @Id
    @Column(name = "id")
    public short getId()
    {
        return id;
    }

    public void setId(short id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "tipo_barco")
    public String getTipoBarco()
    {
        return tipoBarco;
    }

    public void setTipoBarco(String tipoBarco)
    {
        this.tipoBarco = tipoBarco;
    }

    @Basic
    @Column(name = "tamanio")
    public short getTamanio()
    {
        return tamanio;
    }

    public void setTamanio(short tamanio)
    {
        this.tamanio = tamanio;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barcos barcos = (Barcos) o;

        if (id != barcos.id) return false;
        if (tamanio != barcos.tamanio) return false;
        if (tipoBarco != null ? !tipoBarco.equals(barcos.tipoBarco) : barcos.tipoBarco != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) id;
        result = 31 * result + (tipoBarco != null ? tipoBarco.hashCode() : 0);
        result = 31 * result + (int) tamanio;
        return result;
    }
}
