package br.com.academy.luizpedro.mercadolivre.model;

import br.com.academy.luizpedro.mercadolivre.dto.CaracteristicaRequest;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany (mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Imagens> imagens = new ArrayList<>();

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Produto(){}

    public Produto(String nome, BigDecimal valor, Integer qtde, String descricao,
                   Categoria categoria, Usuario usuariologado,
                   List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuariologado;
        caracteristicas.forEach(caracteristica -> this.caracteristicas.add(caracteristica.toModel(this)));
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void associaImagens(List<String> urls) {
        List<Imagens> imagens = urls.stream().map(url -> new Imagens(this, url)).collect(Collectors.toList());
        this.imagens.addAll(imagens);
    }
}