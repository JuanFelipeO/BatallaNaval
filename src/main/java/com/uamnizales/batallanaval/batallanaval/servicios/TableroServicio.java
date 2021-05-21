package com.uamnizales.batallanaval.batallanaval.servicios;

import com.uamnizales.batallanaval.batallanaval.modelo.dto.CasillaBarco;
import com.uamnizales.batallanaval.batallanaval.modelo.dto.Coordenada;
import com.uamnizales.batallanaval.batallanaval.modelo.dto.RespuestaDTO;
import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Barcos;
import com.uamnizales.batallanaval.batallanaval.modelo.entidades.Usuario;
import com.uamnizales.batallanaval.batallanaval.utilidades.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class TableroServicio
{
    private CasillaBarco[][] tableroBarco;
    private int contadorAciertos = 0;
    private int contadorErrores = 0;
    private int contEscondidos = 0;
    private boolean estadoJuego = false;

    private ListaSEService listaSEService;

    @Autowired
    public TableroServicio(ListaSEService listaSEService)
    {
        this.listaSEService = listaSEService;
    }

    public ResponseEntity<Object> inicializarTablero(int filas, int columnas)
    {
        if (filas < 0 || columnas < 0)
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                    Constants.ERROR_ROWS_COLS_POSITIVE), HttpStatus.CONFLICT);
        }
        tableroBarco = new CasillaBarco[filas][columnas];
        return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,null,null),HttpStatus.CREATED);
    }

    public ResponseEntity<Object> esconderBarco(String codigo, Coordenada coordenada)
    {
        if (coordenada.getFilas() < 0 || coordenada.getColumnas() < 0)
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                    Constants.ERROR_ROWS_COLS_POSITIVE), HttpStatus.CONFLICT);
        }

        Usuario barcoEsconder = listaSEService.encontrarBarcoPorCodigo(codigo);
        if(barcoEsconder!=null)
        {
            if(validarCoordenada(coordenada))
            {
                if(tableroBarco[coordenada.getFilas()][coordenada.getColumnas()]==null)
                {
                    tableroBarco[coordenada.getFilas()][coordenada.getColumnas()]=
                            new CasillaBarco(barcoEsconder,false);
                    contEscondidos++;
                    if(contEscondidos == listaSEService.contarNodos())
                    {
                        estadoJuego=true;
                    }
                    return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,null,null),
                            HttpStatus.ACCEPTED);

                }
                else
                {
                    return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_BOX_OCUPATED,null,
                            Constants.ERROR_BOX_OCUPATED), HttpStatus.CONFLICT);
                }
            }
            else
            {
                return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_COORD_NOT_VALIDATE,null,
                        Constants.ERROR_COORD_NOT_VALIDATE), HttpStatus.CONFLICT);
            }
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.DATA_NOT_FOUND,
                    null, Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);
        }
    }


    private boolean validarCoordenada(Coordenada coord)
    {
        if(coord.getFilas() < tableroBarco.length && coord.getColumnas() < tableroBarco[0].length)
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<Object> visualizarTablero()
    {
        if(tableroBarco == null)
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_BOARD_VOID,null,
                    Constants.ERROR_BOARD_VOID), HttpStatus.CONFLICT);
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL,tableroBarco,null), HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> buscarBarco(Coordenada coord)
    {
        if(estadoJuego)
        {
            if(validarCoordenada(coord))
            {
                if(tableroBarco[coord.getFilas()][coord.getColumnas()]!=null
                        && !tableroBarco[coord.getFilas()][coord.getColumnas()].isMarcada())
                {
                    tableroBarco[coord.getFilas()][coord.getColumnas()].setMarcada(true);
                    contadorAciertos++;
                    return this.validarEstadoJuego(true,
                            tableroBarco[coord.getFilas()][coord.getColumnas()].getBarcos());
                }
                else
                {
                    contadorErrores++;
                    return this.validarEstadoJuego(false,null);
                }

            }
            else
            {
                return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_COORD_NOT_VALIDATE,null,
                        Constants.ERROR_COORD_NOT_VALIDATE), HttpStatus.CONFLICT);
            }
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.MESSAGE_STATE_GAME_INACTIVE,null,
                    Constants.ERROR_STATE_GAME_INACTIVE), HttpStatus.CONFLICT);
        }
    }


    private ResponseEntity<Object> validarEstadoJuego(boolean exito, Barcos barcos)
    {
        if(exito)
        {
            if(contadorAciertos== listaSEService.contarNodos())
            {
                estadoJuego=false;
                tableroBarco=null;
                return new ResponseEntity<>(new RespuestaDTO("Has ganado el juego", null,null),
                        HttpStatus.OK);

            }
            else
            {
                return new ResponseEntity<>(new RespuestaDTO(Constants.SUCCESSFUL, barcos,null), HttpStatus.OK);
            }
        }
        else
        {

            if(contadorErrores >= this.listaSEService.contarNodos() * Constants.PERCENTAGE_ERROR_GAME)
            {
                estadoJuego=false;
                tableroBarco=null;
                return new ResponseEntity<>(new RespuestaDTO("HAS PERDIDO",null,
                        "HA SUPERADO EL NUMERO DE OPCIONES POSIBLES"), HttpStatus.CONFLICT);
            }
            else
            {
                return new ResponseEntity<>(new RespuestaDTO("Has fallado",null,null ),
                        HttpStatus.CONFLICT);
            }
        }
    }
}

