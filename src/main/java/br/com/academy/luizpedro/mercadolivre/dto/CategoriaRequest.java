package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.controller.validation.UniqueValue;
import br.com.academy.luizpedro.mercadolivre.model.Categoria;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    String nome;

    Long idCtgMae;

    public String getNome() {
        return nome;
    }

    public Long getIdCtgMae() {
        return idCtgMae;
    }

    public Categoria toModel(@Autowired CategoriaRepository categoriaRepository){
        if(idCtgMae == null)
            return new Categoria(nome);
        Categoria ctgMae = categoriaRepository.findById(idCtgMae).get();
        return new Categoria(nome, ctgMae);
    }
}
