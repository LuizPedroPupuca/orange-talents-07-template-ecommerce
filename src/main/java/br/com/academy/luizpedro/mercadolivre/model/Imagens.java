package br.com.academy.luizpedro.mercadolivre.model;

import javax.persistence.*;

@Entity
public class Imagens {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private String url;

    @Deprecated
    public Imagens(){}

    public Imagens(Produto produto, String url) {
        this.produto = produto;
        this.url = url;
    }
}
