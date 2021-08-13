package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.model.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    private int qtde;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequest(int qtde, Long idProduto, GatewayPagamento gateway) {
        this.qtde = qtde;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public int getQtde() {
        return qtde;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
