package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.controller.validation.ExistId;
import br.com.academy.luizpedro.mercadolivre.model.Pergunta;
import br.com.academy.luizpedro.mercadolivre.model.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    @NotNull @ExistId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;


    public PerguntaRequest(String titulo, Long idProduto) {
        this.titulo = titulo;
        this.idProduto = idProduto;

    }

    public String getTitulo() {
        return titulo;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
