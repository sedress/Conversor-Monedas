package com.cursoalura.conversor.principal;

import com.cursoalura.conversor.servicio.ExchangeRateService;

import java.io.IOException;
import java.util.Scanner;

public class ConversorMonedasApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateService service = new ExchangeRateService();
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1 -> convertirMoneda(service, "COP", "USD", "pesos colombianos", "dólares", scanner);
                case 2 -> convertirMoneda(service, "COP", "MXN", "pesos colombianos", "pesos mexicanos", scanner);
                case 3 -> convertirMoneda(service, "USD", "COP", "dólares", "pesos colombianos", scanner);
                case 4 -> convertirMoneda(service, "USD", "MXN", "dólares", "pesos mexicanos", scanner);
                case 5 -> convertirMoneda(service, "MXN", "USD", "pesos mexicanos", "dólares", scanner);
                case 6 -> convertirMoneda(service, "MXN", "COP", "pesos mexicanos", "pesos colombianos", scanner);
                case 7 -> {
                    System.out.println("¡Gracias por usar el conversor de monedas! ¡Hasta pronto!");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n*********************************************************");
        System.out.println("Bienvenido a este conversor de monedas, elija una de las siguientes opciones:");
        System.out.println("*********************************************************");
        System.out.println("1) peso colombiano => dólar");
        System.out.println("2) peso colombiano => peso mexicano");
        System.out.println("3) dólar => peso colombiano");
        System.out.println("4) dólar => peso mexicano");
        System.out.println("5) peso mexicano => dólar");
        System.out.println("6) peso mexicano => colombiano");
        System.out.println("7) salir");
        System.out.println("*********************************************************");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcion(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar el buffer
            System.out.println("Por favor, ingrese un número válido.");
            return -1; // Retorna un valor inválido
        }
    }

    private static void convertirMoneda(ExchangeRateService service, String monedaOrigen, String monedaDestino,
                                        String nombreOrigen, String nombreDestino, Scanner scanner) {
        System.out.print("Digite la cantidad a convertir: ");
        try {
            double cantidad = scanner.nextDouble();
            double tasa = service.obtenerTasaCambio(monedaOrigen, monedaDestino);
            double resultado = cantidad * tasa;
            System.out.printf("%,.2f %s equivalen a: %,.2f %s\n", cantidad, nombreOrigen, resultado, nombreDestino);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }
}
