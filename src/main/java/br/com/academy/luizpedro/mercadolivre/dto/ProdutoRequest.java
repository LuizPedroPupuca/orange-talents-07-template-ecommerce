package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.controller.validation.ExistId;
import br.com.academy.luizpedro.mercadolivre.model.Caracteristica;
import br.com.academy.luizpedro.mercadolivre.model.Categoria;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @Positive @NotNull
    private BigDecimal valor;
    @PositiveOrZero
    private Integer qtde;

    @NotBlank @Length(max = 1000)
    private String descricao;

    @NotNull @ExistId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(String nome, BigDecimal valor, Integer qtde, String descricao, Long idCategoria, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtde = qtde;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public boolean temCaracteristicasIguais(){
        List<String> caracteristicasNomesCompara = new ArrayList<>();
        for(CaracteristicaRequest caracteristica : caracteristicas){
            if(caracteristicasNomesCompara.contains(caracteristica.getNome())){
                return true;
            }
            caracteristicasNomesCompara.add(caracteristica.getNome());
        }
        return false;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario){

        Categoria categoria = categoriaRepository.findById(idCategoria).get();
        return new Produto(nome, valor, qtde, descricao, categoria ,usuario, caracteristicas);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQtde() {
        return qtde;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }
}
