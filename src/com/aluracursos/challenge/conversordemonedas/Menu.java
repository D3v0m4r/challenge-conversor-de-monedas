package com.aluracursos.challenge.conversordemonedas;

import java.util.Scanner;

public class Menu {

    private static final String[] CURRENCIES = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    private CurrencyConverter currencyConverter;
    private Scanner scanner;

    public Menu() {
        this.currencyConverter = new CurrencyConverter();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Bienvenido al Conversor de Monedas!");
        System.out.println("Monedas disponibles:");
        for (int i = 0; i < CURRENCIES.length; i++) {
            System.out.println((i + 1) + ". " + CURRENCIES[i]);
        }
        System.out.println("0. Salir");

        while (true) {
            try {
                System.out.print("\nSeleccione la moneda de origen (número): ");
                int fromChoice = Integer.parseInt(scanner.nextLine().trim());

                if (fromChoice == 0) {
                    break;
                } else if (fromChoice < 0 || fromChoice > CURRENCIES.length) {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;
                }

                System.out.print("Ingrese la cantidad a convertir en " + CURRENCIES[fromChoice - 1] + ": ");
                double amount = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Seleccione la moneda a la cual desea convertir (número): ");
                int toChoice = Integer.parseInt(scanner.nextLine().trim());

                if (toChoice < 1 || toChoice > CURRENCIES.length || toChoice == fromChoice) {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;
                }

                String fromCurrency = CURRENCIES[fromChoice - 1];
                String toCurrency = CURRENCIES[toChoice - 1];

                double convertedAmount = currencyConverter.convertCurrency(amount, fromCurrency, toCurrency);
                System.out.printf("%.2f %s equivale a %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
            } catch (Exception e) {
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        }

        System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
        scanner.close();
    }
}
