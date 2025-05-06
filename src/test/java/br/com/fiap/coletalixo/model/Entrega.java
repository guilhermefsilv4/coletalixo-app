package br.com.fiap.coletalixo.model;

import java.time.LocalDate;

/**
 * Modelo que representa uma entrega de resíduos no sistema.
 */
public class Entrega {
    private String id;
    private String email;
    private LocalDate dataEntrega;
    private String enderecoColeta;
    private String tipoResiduo;
    private String quantidade;
    private String status;

    /**
     * Construtor padrão
     */
    public Entrega() {
    }

    /**
     * Construtor com todos os campos
     */
    public Entrega(String id, String email, LocalDate dataEntrega, String enderecoColeta, 
                  String tipoResiduo, String quantidade, String status) {
        this.id = id;
        this.email = email;
        this.dataEntrega = dataEntrega;
        this.enderecoColeta = enderecoColeta;
        this.tipoResiduo = tipoResiduo;
        this.quantidade = quantidade;
        this.status = status;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getEnderecoColeta() {
        return enderecoColeta;
    }

    public void setEnderecoColeta(String enderecoColeta) {
        this.enderecoColeta = enderecoColeta;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", dataEntrega=" + dataEntrega +
                ", enderecoColeta='" + enderecoColeta + '\'' +
                ", tipoResiduo='" + tipoResiduo + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
} 