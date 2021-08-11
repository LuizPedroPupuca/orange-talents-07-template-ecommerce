package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDetalhesResponse {

    private List<ImagemResponse> imagens;

    private String nome;

    private BigDecimal valor;

    private List<CaracteristicaResponse> caracteristicas;

    private String descricao;

    private Double mediaNotas;

    private Integer numeroTotalNotas;

    private List<OpiniaoResponse> opinioes;

    private List<PerguntaResponse> perguntas;

    public ProdutoDetalhesResponse(Produto produto) {
        this.imagens = produto.getImagens().stream().map(ImagemResponse::new).collect(Collectors.toList());
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse:: new).collect(Collectors.toList());
        this.descricao = produto.getDescricao();
        this.mediaNotas = produto.getOpinioes().stream().map(opiniao -> opiniao.getNota())
               .mapToDouble(Integer::doubleValue).average().orElse(0.0);
        this.numeroTotalNotas = produto.getOpinioes().stream().map(opiniao -> opiniao.getNota())
                .collect(Collectors.toList()).size();
               //.reduce(0, Integer::sum);
        this.opinioes = produto.getOpinioes().stream().map(OpiniaoResponse:: new).collect(Collectors.toList());
        this.perguntas = produto.getPerguntas().stream().map((PerguntaResponse::new)).collect(Collectors.toList());
    }

    public List<ImagemResponse> getImagens() {
        return imagens;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getNumeroTotalNotas() {
        return numeroTotalNotas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
