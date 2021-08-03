package br.com.academy.luizpedro.mercadolivre.controller;

import br.com.academy.luizpedro.mercadolivre.dto.CategoriaRequest;
import br.com.academy.luizpedro.mercadolivre.dto.UsuarioRequest;
import br.com.academy.luizpedro.mercadolivre.model.Categoria;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().body("Categoria cadastrada com sucesso");
    }
}
