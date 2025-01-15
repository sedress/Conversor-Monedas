package com.cursoalura.conversor.servicio;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ExchangeRateService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c870c54afebef750f124b550/latest/";

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        // Construir la URL de la API
        String urlCompleta = API_URL + monedaOrigen;
        URL url = new URL(urlCompleta);

        // Realizar la conexión HTTP
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.connect();

        // Validar el código de respuesta HTTP
        int codigoRespuesta = conexion.getResponseCode();
        if (codigoRespuesta != 200) {
            throw new IOException("Error al conectar con la API. Código de respuesta: " + codigoRespuesta);
        }

        // Leer la respuesta
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder respuestaJson = new StringBuilder();
        while (scanner.hasNext()) {
            respuestaJson.append(scanner.nextLine());
        }
        scanner.close();

        // Parsear la respuesta JSON
        JsonObject jsonRespuesta = JsonParser.parseString(respuestaJson.toString()).getAsJsonObject();
        JsonObject tasas = jsonRespuesta.getAsJsonObject("conversion_rates");

        // Obtener la tasa de cambio de la moneda de destino
        if (!tasas.has(monedaDestino)) {
            throw new IllegalArgumentException("No se encontró la tasa de cambio para la moneda destino: " + monedaDestino);
        }

        return tasas.get(monedaDestino).getAsDouble();
    }
}
