package conversionmonedas.principal;

import conversionmonedas.api.ConexionApi;
import conversionmonedas.historial.HistorialConversiones;
import conversionmonedas.modelos.Conversion;
import conversionmonedas.modelos.Historial;
import conversionmonedas.modelos.Moneda;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PrincipalMenu {
    ConexionApi conexion = new ConexionApi();
    Conversion conversion;
    HistorialConversiones historial = new HistorialConversiones();
    Scanner entrada = new Scanner(System.in);

    public void muestraMenu() {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("**********************************************");
            System.out.println("Conversor de monedas");
            System.out.println("0) Ver todas las menedas disponible");
            System.out.println("1) Ver las conversiones mas usadas");
            System.out.println("2) Realizar una conversion personalizada");
            System.out.println("3) Ver el historial de conversiones");
            System.out.println("4) Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = entrada.nextInt();
            } catch (InputMismatchException e) {
                entrada.next();
            }
            System.out.println("**********************************************");

            switch (opcion) {
                case 0:
                    mostrarMonedasDisponibles();
                    break;
                case 1:
                    verConversionesMasUsadas();
                    break;
                case 2:
                    conversionPersonalizada();
                    break;
                case 3:
                    verHistorialConversiones();
                    break;
                case 4:
                    System.out.println("Guardando los datos");
                    historial.guardarConversiones();
                    System.out.println("Cerrando programa..");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    entrada.nextLine();
            }
        }
    }

    private void verConversionesMasUsadas() {
        int opcion = 0;
        double cantidad = 0;
        double resultado = 0;

        System.out.println("Conversiones mas usadas");
        System.out.println("1) Dolar => Peso Argentino");
        System.out.println("2) Peso Argentino => Dolar");
        System.out.println("3) Peso Argentino => Real Brasil");
        System.out.println("4) Real Brasil => Peso Argentino");
        System.out.println("5) Dolar => Real Brasil");
        System.out.println("6) Real Brasil => Dolar");
        System.out.print("Seleccione una opcion: ");
        try {
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            entrada.next();
        }
        System.out.println("**********************************************");

        switch (opcion) {
            case 1:
                System.out.println("Ingrese la cantidad de USD (Dolar)");
                cantidad = entrada.nextDouble();
                conversion = conexion.getConversion("USD", "ARS");
                resultado = (conversion.conversion_rate() * cantidad);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion("USD", "ARS", resultado);
                break;
            case 2:
                System.out.println("Ingrese la cantidad de ARS (Peso Argentino)");
                cantidad = entrada.nextDouble();
                conversion = conexion.getConversion("ARS", "USD");
                resultado = (conversion.conversion_rate() * cantidad);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion("ARS", "USD", resultado);
                break;
            case 3:
                System.out.println("Ingrese la cantidad de ARS (Peso Argentino)");
                cantidad = entrada.nextDouble();
                conversion = conexion.getConversion("ARS", "BRL");
                resultado = (conversion.conversion_rate() * cantidad);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion("ARS", "BRL", resultado);
                break;
            case 4:
                System.out.println("Ingrese la cantidad de BRA (Real Brasil)");
                cantidad = entrada.nextDouble();
                conversion = conexion.getConversion("BRL", "USD");
                resultado = (conversion.conversion_rate() * cantidad);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion("BRL", "USD", resultado);
                break;
            case 5:
                System.out.println("Ingrese la cantidad de USD (Dolar)");
                cantidad = entrada.nextDouble();
                conversion = conexion.getConversion("USD", "BRL");
                resultado = (conversion.conversion_rate() * cantidad);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion("USD", "BRL", resultado);
                break;
            case 6:
                System.out.println("Ingrese la cantidad de BRA (Real Brasil)");
                cantidad = entrada.nextDouble();
                conversion = conexion.getConversion("BRL", "USD");
                resultado = (conversion.conversion_rate() * cantidad);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion("BRL", "USD", resultado);
                break;
            default:
                System.out.println("Ingrese una opcion valida");
                entrada.nextLine();
        }
    }

    private void mostrarMonedasDisponibles() {
        System.out.println("Monedas disponibles");
        Moneda moneda = conexion.getMonedas();
        for (Object key : moneda.conversion_rates().keySet()) {
            System.out.println("Currency = " + key);
        }
    }

    private void conversionPersonalizada() {
        System.out.println("Conversion personalizada");
        System.out.print("Ingrese la signa de la moneda origen: ");
        String monedaOrigen = entrada.next();
        System.out.print("Ingrese la signa de la moneda destino: ");
        String monedaDestino = entrada.next();
        System.out.print("Ingrese el valor a convertir: ");
        double valor = entrada.nextDouble();
        try {
            conversion = conexion.getConversion(monedaOrigen.toUpperCase(), monedaDestino.toUpperCase());
            if (conversion.conversion_rate() != 0.0) {
                double resultado = (conversion.conversion_rate() * valor);
                System.out.println("Resultado: " + resultado);
                historial.agregarConversion(monedaOrigen.toUpperCase(), monedaDestino.toUpperCase(), resultado);
            } else {
                System.out.println("Los valores ingresados son incorrectos");
            }
        } catch(Exception e) {
            System.out.println("Las monedas ingresadas son incorrectas");
        }
    }

    private void verHistorialConversiones() {
        System.out.println("Historial de conversiones");
        List<Historial> historialConveriones = historial.obtenerHistorial();
        historialConveriones.forEach(System.out::println);
    }
}
