package com.uamnizales.batallanaval.batallanaval.modelo.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Juego
{
    private short codigoPartida;
    private Date fecha;

    @Id
    @Column(name = "codigo_partida")
    public short getCodigoPartida()
    {
        return codigoPartida;
    }

    public void setCodigoPartida(short codigoPartida)
    {
        this.codigoPartida = codigoPartida;
    }

    @Basic
    @Column(name = "fecha")
    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Juego juego = (Juego) o;

        if (codigoPartida != juego.codigoPartida) return false;
        if (fecha != null ? !fecha.equals(juego.fecha) : juego.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) codigoPartida;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
