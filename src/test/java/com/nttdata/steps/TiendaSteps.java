package com.nttdata.steps;

import com.nttdata.screens.TiendaScreen;
import org.junit.Assert;

public class TiendaSteps {

    private final TiendaScreen screen = new TiendaScreen();

    public void validarGaleria() {
        boolean visible = screen.galeriaVisible();
        Assert.assertTrue("La galería de productos no cargó correctamente", visible);
    }

    public void agregarProductoYUnidades(int unidades, String producto) {
        screen.seleccionarProducto(producto);
        screen.agregarUnidades(unidades);
    }

    public void validarCarrito(int unidadesEsperadas) {
        screen.irAlCarrito();
        boolean esValido = screen.validarCantidadCarrito(unidadesEsperadas);
        Assert.assertTrue("El carrito no muestra la cantidad de producto esperada", esValido);
    }
}