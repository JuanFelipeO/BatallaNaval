package com.uamnizales.batallanaval.batallanaval.controlador;

import com.uamnizales.batallanaval.batallanaval.servicios.ListaSEService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/batallanaval")
@Validated

public class BatallaNavalCotrolador
{
    private ListaSEService listaSEService;

    public BatallaNavalCotrolador(ListaSEService listaSEService)
    {
        this.listaSEService = listaSEService;
    }

    @GetMapping
    public String initUsuarioListaSE()
    {
        listaSEService.cargar();
        return listaSEService.listarNodos();
    }
}
