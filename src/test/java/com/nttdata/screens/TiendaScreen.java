package com.nttdata.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static com.nttdata.screens.AppConfigScreen.getDriver;

public class TiendaScreen {

    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    public boolean galeriaVisible() {
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        WebElement tituloGaleria = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV"))
        );
        return tituloGaleria.isDisplayed();
    }

    public void seleccionarProducto(String producto) {
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        // Búsqueda por Accessibility ID
        WebElement elProducto = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(producto))
        );
        elProducto.click();
    }

    public void agregarUnidades(int unidades) {
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        // Busqueda del botón de sumar
        WebElement btnSumar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                                        "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/plusIV\"));"
                        )
                )
        );

        if (unidades > 1) {
            for (int i = 1; i < unidades; i++) {
                btnSumar.click();
            }
        }

        WebElement btnAddCart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiScrollable(new UiSelector().scrollable(true))" +
                                        ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/cartBt\"));"
                        )
                )
        );
        btnAddCart.click();
    }

    public void irAlCarrito() {
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        WebElement iconoCarrito = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/cartIV"))
        );
        iconoCarrito.click();
    }

    public boolean validarCantidadCarrito(int cantidadEsperada) {
        AndroidDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        WebElement txtCantidad = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/noTV"))
        );
        String cantidadActual = txtCantidad.getText();
        return cantidadActual.equals(String.valueOf(cantidadEsperada));
    }
}