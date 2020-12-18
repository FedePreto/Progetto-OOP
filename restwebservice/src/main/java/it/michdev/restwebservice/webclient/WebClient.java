package it.michdev.restwebservice.webclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * La classe astratta <b>WebClient</b> rappresenta un generico REST client con
 * metodi per effettuare chiamate ad un endpoint definito. Questa classe è
 * estesa da <code>FxMarketClient</code>.
 * 
 * @version 0.8.0
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.webclient.FxMarketClient
 */
public abstract class WebClient {

    protected String endpoint;
    private HttpClient httpClient;

    /**
     * Costruttore per la classe <code>WebClient</code>
     * 
     * @param endpoint endpoint a cui effettuare le chiamate HTTP REST.
     */
    public WebClient(String endpoint) {
        this.endpoint = endpoint;
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Effettua una chiamata all'endpoint definito tramite <code>URI</code> passata
     * come argomento e scarica i dati ottenuti come oggetto
     * <code>HttpResponse</code>.
     * 
     * @param requestUri <code>URI</code> dell'endpoint
     * @return oggetto <code>HttpResponse</code> contenente i dati ottenuti.
     */
    protected HttpResponse<String> downloadData(URI requestUri) {
        HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).build();
        HttpResponse<String> requestResponse;
        try {
            requestResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return requestResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo astratto, implementato nelle sottoclassi di <code>WebClient</code>.
     * Costruisce un <code>URI</code> ed ottiene i dati della chiamata effettuata.
     * 
     * @return oggetto <code>HttpResponse</code> contenente i dati ottenuti.
     */
    public abstract HttpResponse<String> requestData();
}
