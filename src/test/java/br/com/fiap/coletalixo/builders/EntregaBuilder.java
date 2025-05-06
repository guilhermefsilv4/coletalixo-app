package br.com.fiap.coletalixo.builders;

import br.com.fiap.coletalixo.model.Entrega;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Builder para facilitar a criação de objetos Entrega durante os testes.
 */
public class EntregaBuilder {
    private String id;
    private String email;
    private LocalDate dataEntrega;
    private String enderecoColeta;
    private String tipoResiduo;
    private String quantidade;
    private String status;

    /**
     * Construtor que inicializa com valores padrão
     */
    public EntregaBuilder() {
        this.id = "entrega123";
        this.email = "usuario@teste.com";
        this.dataEntrega = LocalDate.now();
        this.enderecoColeta = "Rua Teste, 123";
        this.tipoResiduo = "RECICLAVEL";
        this.quantidade = "10kg";
        this.status = "PENDENTE";
    }

    /**
     * Define o ID da entrega
     * 
     * @param id ID da entrega
     * @return Instância atual do builder
     */
    public EntregaBuilder comId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Define o email do usuário
     * 
     * @param email Email do usuário
     * @return Instância atual do builder
     */
    public EntregaBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Define a data da entrega
     * 
     * @param dataEntrega Data da entrega no formato yyyy-MM-dd
     * @return Instância atual do builder
     */
    public EntregaBuilder comDataEntrega(String dataEntrega) {
        this.dataEntrega = LocalDate.parse(dataEntrega, DateTimeFormatter.ISO_LOCAL_DATE);
        return this;
    }

    /**
     * Define o endereço de coleta
     * 
     * @param enderecoColeta Endereço de coleta
     * @return Instância atual do builder
     */
    public EntregaBuilder comEnderecoColeta(String enderecoColeta) {
        this.enderecoColeta = enderecoColeta;
        return this;
    }

    /**
     * Define o tipo de resíduo
     * 
     * @param tipoResiduo Tipo de resíduo
     * @return Instância atual do builder
     */
    public EntregaBuilder comTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
        return this;
    }

    /**
     * Define a quantidade
     * 
     * @param quantidade Quantidade de resíduo
     * @return Instância atual do builder
     */
    public EntregaBuilder comQuantidade(String quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    /**
     * Define o status da entrega
     * 
     * @param status Status da entrega
     * @return Instância atual do builder
     */
    public EntregaBuilder comStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Constrói um objeto Entrega com os valores configurados
     * 
     * @return Objeto Entrega
     */
    public Entrega build() {
        return new Entrega(id, email, dataEntrega, enderecoColeta, tipoResiduo, quantidade, status);
    }
} 