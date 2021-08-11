package br.com.academy.luizpedro.mercadolivre.model;

import javax.persistence.*;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private int qtde;

    @ManyToOne
    private Usuario comprador;

    @Enumerated
    private GatewayPagamento gateway;

    public Compra(Produto produto, int qtde, Usuario comprador, GatewayPagamento gateway) {
        this.produto = produto;
        this.qtde = qtde;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "produto=" + produto +
                ", qtde=" + qtde +
                ", comprador=" + comprador +
                '}';
    }
}
