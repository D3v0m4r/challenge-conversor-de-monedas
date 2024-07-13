package com.aluracursos.challenge.conversordemonedas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CurrencyConverter {

    private HttpClient httpClient;
    private Gson gson;

    public CurrencyConverter() {
        this.httpClient = new HttpClient();
        this.gson = new Gson();
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) throws Exception {
        String jsonData = httpClient.getExchangeRates();
        JsonObject exchangeRates = gson.fromJson(jsonData, JsonObject.class);

        double rateFrom = exchangeRates.getAsJsonObject("conversion_rates").get(fromCurrency).getAsDouble();
        double rateTo = exchangeRates.getAsJsonObject("conversion_rates").get(toCurrency).getAsDouble();

        // Convertir la cantidad de fromCurrency a USD primero, luego de USD a toCurrency
        double amountInUSD = amount / rateFrom;
        return amountInUSD * rateTo;
    }
}