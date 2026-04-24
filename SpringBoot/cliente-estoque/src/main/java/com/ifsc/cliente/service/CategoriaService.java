package com.ifsc.cliente.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifsc.cliente.model.Categoria;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CategoriaService {

    private static final String API_URL = "http://172.16.112.143:8080/categorias";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    // GET: Busca todas as categorias no Spring Boot
    public List<Categoria> listar() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Gson traduz a String JSON recebida para uma Lista de Categoria do Java
        return gson.fromJson(response.body(), new TypeToken<List<Categoria>>(){}.getType());
    }

    // POST ou PUT: Salva ou atualiza uma categoria
    public void salvar(Categoria categoria) throws Exception {
        String json = gson.toJson(categoria);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .header("Content-Type", "application/json");

        if (categoria.getId() == null) {
            builder.uri(URI.create(API_URL)).POST(HttpRequest.BodyPublishers.ofString(json));
        } else {
            builder.uri(URI.create(API_URL + "/" + categoria.getId()))
                    .PUT(HttpRequest.BodyPublishers.ofString(json));
        }
        client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    // DELETE: Exclui no servidor
    public void excluir(Integer id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/" + id))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
