package com.nttdata.hooks;

import com.nttdata.steps.AppConfigSteps;
import com.nttdata.support.ScreenshotAttacher;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks globales de Cucumber: aplican a TODOS los escenarios, sin
 * importar el feature. Utiles para dar contexto en consola al
 * aprendiz sobre en que punto va la ejecucion.
 */
public class Hooks {

    private final AppConfigSteps appConfigSteps = new AppConfigSteps();
    private Scenario scenario;

    @Before
    public void antesDelEscenario(Scenario scenario) {
        this.scenario = scenario;
        // Inicializa el driver para el primer escenario
        try {
            com.nttdata.screens.AppConfigScreen.getDriver();
        } catch (IllegalStateException e) {
            appConfigSteps.iniciarAplicacion("saucelabs.properties");
        }

        System.out.println("========================================");
        System.out.println("[Hooks] Iniciando escenario: " + scenario.getName());
        System.out.println("========================================");
    }

    @After
    public void despuesDelEscenario(Scenario scenario) {
        String estado = scenario.isFailed() ? "FALLIDO" : "EXITOSO";
        System.out.println("[Hooks] Escenario '" + scenario.getName() + "' finalizo: " + estado);

        // Tomamos y adjuntamos la evidencia ANTES de cerrar el driver
        try {
            byte[] screenshot = com.nttdata.screens.AppConfigScreen.getDriver().getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            String nombreEvidencia = scenario.isFailed() ? "Estado final (FALLO)" : "Estado final (EXITOSO)";
            scenario.attach(screenshot, "image/png", nombreEvidencia);
        } catch (Exception e) {
            System.out.println("[Hooks] Advertencia al tomar evidencia final: " + e.getMessage());
        }

        // Cerramos la aplicación/driver al terminar cada escenario para evitar bucles o sesiones fantasmas
        try {
            com.nttdata.screens.AppConfigScreen.cerrarDriver();
        } catch (Exception e) {
            System.out.println("[Hooks] Advertencia al cerrar el driver: " + e.getMessage());
        }
    }
}
