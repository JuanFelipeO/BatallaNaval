package com.uamnizales.batallanaval.batallanaval.modelo;

import java.io.Serializable;

public class Nodo implements Serializable
{
    private Object dato;
    private Nodo siguiente;

    public Nodo(Object dato)
    {
        this.dato = dato;
    }

    public Object getDato()
    {
        return dato;
    }

    public void setDato(Object dato)
    {
        this.dato = dato;
    }

    public Nodo getSiguiente()
    {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente)
    {
        this.siguiente = siguiente;
    }
}
