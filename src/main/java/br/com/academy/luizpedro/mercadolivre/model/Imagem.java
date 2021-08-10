package br.com.academy.luizpedro.mercadolivre.model;

import javax.persistence.*;

@Entity
public class Imagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private String url;

    @Deprecated
    public Imagem(){}

    public Imagem(Produto produto, String url) {
        this.produto = produto;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
