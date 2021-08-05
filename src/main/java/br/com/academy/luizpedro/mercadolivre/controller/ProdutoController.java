package br.com.academy.luizpedro.mercadolivre.controller;
import br.com.academy.luizpedro.mercadolivre.dto.ProdutoRequest;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.CategoriaRepository;
import br.com.academy.luizpedro.mercadolivre.repository.ProdutoRepository;
import br.com.academy.luizpedro.mercadolivre.repository.UsuarioRepository;
import br.com.academy.luizpedro.mercadolivre.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    ResponseEntity<?> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = produtoRequest.toModel(categoriaRepository, usuarioLogado.get());
        produtoRepository.save(produto);
        return ResponseEntity.ok().body("Produto cadastrado com sucesso pelo usuário "+usuarioLogado.getUsername());
    }
}
