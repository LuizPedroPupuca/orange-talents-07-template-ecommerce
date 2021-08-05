package br.com.academy.luizpedro.mercadolivre.model;

import br.com.academy.luizpedro.mercadolivre.dto.CaracteristicaRequest;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer qtde;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario usuario;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Produto(String nome, BigDecimal valor, Integer qtde, String descricao, Categoria categoria, Usuario usuariologado, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuariologado;
        caracteristicas.forEach(caracteristica -> this.caracteristicas.add(caracteristica.toModel(this)));
    }

}
