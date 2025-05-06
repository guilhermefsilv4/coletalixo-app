package br.com.fiap.coletalixo.service;

import org.json.JSONObject;
import org.json.JSONException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.google.gson.Gson;

import java.util.Map;
import java.util.HashMap;

/**
 * Classe que encapsula a lógica de negócio relacionada ao cadastro de entregas.
 * Responsável por configurar os campos da entrega e realizar requisições à API.
 */
public class CadastroEntregasService {

    private HashMap<String, Object> entregaModel;
    private Gson gson;
    private Response response;
    private String baseUrl;
    private String token;

    /**
     * Construtor que inicializa as variáveis necessárias para o cadastro de entregas.
     * 
     * @param baseUrl URL base da API
     * @param token Token de autenticação do usuário
     */
    public CadastroEntregasService(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.entregaModel = new HashMap<>();
        this.gson = new Gson();
    }

    /**
     * Configura os campos da entrega com base nos dados fornecidos.
     * 
     * @param dataEntrega Data da entrega
     * @param enderecoColeta Endereço de coleta
     * @param tipoResiduo Tipo de resíduo
     * @param quantidade Quantidade de resíduo
     * @return Instância atual do serviço para encadeamento de métodos
     */
    public CadastroEntregasService setFieldsDelivery(String dataEntrega, String enderecoColeta, 
                                                    String tipoResiduo, String quantidade) {
        entregaModel.put("dataEntrega", dataEntrega);
        entregaModel.put("enderecoColeta", enderecoColeta);
        entregaModel.put("tipoResiduo", tipoResiduo);
        entregaModel.put("quantidade", quantidade);
        return this;
    }

    /**
     * Configura campos adicionais da entrega.
     * 
     * @param fieldName Nome do campo
     * @param fieldValue Valor do campo
     * @return Instância atual do serviço para encadeamento de métodos
     */
    public CadastroEntregasService setField(String fieldName, String fieldValue) {
        entregaModel.put(fieldName, fieldValue);
        return this;
    }

    /**
     * Configura os campos da entrega a partir de um mapa de dados.
     * 
     * @param fields Mapa com os campos e valores da entrega
     * @return Instância atual do serviço para encadeamento de métodos
     */
    public CadastroEntregasService setFieldsFromMap(Map<String, String> fields) {
        fields.forEach(entregaModel::put);
        return this;
    }

    /**
     * Envia uma requisição POST para criar uma nova entrega.
     * 
     * @return Resposta da API
     */
    public Response createDelivery() {
        String jsonBody = gson.toJson(entregaModel);
        
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(baseUrl + "/entregas");
        
        return response;
    }

    /**
     * Envia uma requisição PUT para atualizar uma entrega existente.
     * 
     * @param entregaId ID da entrega a ser atualizada
     * @return Resposta da API
     */
    public Response updateDelivery(String entregaId) {
        String jsonBody = gson.toJson(entregaModel);
        
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put(baseUrl + "/entregas/" + entregaId);
        
        return response;
    }

    /**
     * Obtém uma entrega específica pelo ID.
     * 
     * @param entregaId ID da entrega
     * @return Resposta da API
     */
    public Response getDeliveryById(String entregaId) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(baseUrl + "/entregas/" + entregaId);
        
        return response;
    }

    /**
     * Lista todas as entregas do usuário.
     * 
     * @return Resposta da API
     */
    public Response getAllUserDeliveries() {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(baseUrl + "/entregas/minhas-entregas");
        
        return response;
    }

    /**
     * Cancela uma entrega específica.
     * 
     * @param entregaId ID da entrega a ser cancelada
     * @return Resposta da API
     */
    public Response cancelDelivery(String entregaId) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put(baseUrl + "/entregas/" + entregaId + "/cancelar");
        
        return response;
    }

    /**
     * Remove uma entrega específica.
     * 
     * @param entregaId ID da entrega a ser removida
     * @return Resposta da API
     */
    public Response deleteDelivery(String entregaId) {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(baseUrl + "/entregas/" + entregaId);
        
        return response;
    }

    /**
     * Obtém o modelo de entrega atual.
     * 
     * @return Modelo de entrega
     */
    public HashMap<String, Object> getEntregaModel() {
        return this.entregaModel;
    }

    /**
     * Obtém a última resposta da API.
     * 
     * @return Última resposta da API
     */
    public Response getLastResponse() {
        return this.response;
    }
} 