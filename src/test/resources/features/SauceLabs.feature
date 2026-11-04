@RegresionTienda @Carrito
Feature: Validación del carrito de compras en la tienda online

  Scenario Outline: Validar la adición y actualización del carrito de compras
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido el carrito de compra actualice correctamente
    And cierro el aplicativo "com.saucelabs.mydemoapp.android"

    Examples:
      | PRODUCTO                  | UNIDADES |
      | Sauce Labs Backpack       | 1        |
      | Sauce Labs Bolt T-Shirt   | 1        |
      | Sauce Labs Bike Light     | 2        |