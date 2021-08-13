package br.com.academy.luizpedro.mercadolivre.dto;
import br.com.academy.luizpedro.mercadolivre.controller.validation.ExistId;
import br.com.academy.luizpedro.mercadolivre.controller.validation.ExistsQuantity;
import br.com.academy.luizpedro.mercadolivre.model.GatewayPagamento;
import br.com.academy.luizpedro.mercadolivre.model.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ExistsQuantity
public class CompraRequest {
    @NotNull @Positive
    private Integer quantidade;
    @NotNull @ExistId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;
    @NotNull
    private GatewayPagamento gatewayPagamento;

    public CompraRequest(Integer quantidade, Long produtoId,GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.gatewayPagamento = gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
