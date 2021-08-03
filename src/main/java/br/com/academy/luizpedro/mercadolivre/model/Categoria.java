package br.com.academy.luizpedro.mercadolivre.model;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String nome;

    @ManyToOne
    private Categoria ctgMae;

    @Deprecated
    public Categoria(){

    }

    public Categoria(String nome, Categoria ctgMae) {
        this.nome = nome;
        this.ctgMae = ctgMae;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }
}
