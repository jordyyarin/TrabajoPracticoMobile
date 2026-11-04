package com.nttdata.stepsdefinitions;

import com.nttdata.steps.AppConfigSteps;
import com.nttdata.steps.TiendaSteps;
import com.nttdata.support.ScreenshotAttacher;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TiendaStepDefs {

    private final AppConfigSteps appConfigSteps = new AppConfigSteps();
    private final TiendaSteps tiendaSteps = new TiendaSteps();
    private int unidadesEsperadas; // Variable para almacenar la cantidad de productos
    private Scenario scenario;

    @Before
    public void configurarScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicacionDeSauceLabs() {
        //appConfigSteps.iniciarAplicacion("saucelabs.properties");
        ScreenshotAttacher.attach(scenario, "Aplicación iniciada");
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        tiendaSteps.validarGaleria();
        ScreenshotAttacher.attach(scenario, "Galería de productos visible");
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoUnidadesDelSiguienteProducto(int unidades, String producto) {
        this.unidadesEsperadas = unidades;
        tiendaSteps.agregarProductoYUnidades(unidades, producto);
        ScreenshotAttacher.attach(scenario, "Producto agregado: " + producto + " (" + unidades + " und)");
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void validoElCarritoDeCompraActualiceCorrectamente() {
        tiendaSteps.validarCarrito(unidadesEsperadas);
        ScreenshotAttacher.attach(scenario, "Carrito validado correctamente");
    }
}