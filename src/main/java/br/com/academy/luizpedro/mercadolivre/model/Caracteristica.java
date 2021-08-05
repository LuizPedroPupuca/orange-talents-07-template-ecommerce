package br.com.academy.luizpedro.mercadolivre.model;

import javax.persistence.*;

@Entity
public class Caracteristica {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristica(){}


    public Caracteristica(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this. descricao = descricao;
        this.produto = produto;
    }
}
