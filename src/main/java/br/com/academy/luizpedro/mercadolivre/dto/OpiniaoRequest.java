package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.controller.validation.ExistId;
import br.com.academy.luizpedro.mercadolivre.model.Opiniao;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.ProdutoRepository;
import br.com.academy.luizpedro.mercadolivre.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class OpiniaoRequest {

    @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank @Length(max = 500)
    private String descricao;

    @NotNull @ExistId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;


    public OpiniaoRequest(Integer nota, String titulo, String descricao, Long idProduto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


}
