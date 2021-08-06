package br.com.academy.luizpedro.mercadolivre.controller;
import br.com.academy.luizpedro.mercadolivre.dto.ImagemRequest;
import br.com.academy.luizpedro.mercadolivre.dto.ProdutoRequest;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.repository.CategoriaRepository;
import br.com.academy.luizpedro.mercadolivre.repository.ProdutoRepository;
import br.com.academy.luizpedro.mercadolivre.repository.UsuarioRepository;
import br.com.academy.luizpedro.mercadolivre.utils.UploaderSimulador;
import br.com.academy.luizpedro.mercadolivre.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UploaderSimulador uploaderSimulador;

    @PostMapping
    @Transactional
    ResponseEntity<?> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = produtoRequest.toModel(categoriaRepository, usuarioLogado.get());
        produtoRepository.save(produto);
        return ResponseEntity.ok().body("Produto cadastrado com sucesso pelo usuário "+usuarioLogado.getUsername());
    }

    @PostMapping("/{id}/imagem")
    @Transactional
    public ResponseEntity adicionaImagem(@PathVariable @NotNull Long id, @Valid ImagemRequest imagemRequest,
                                         @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        List<String> urls = uploaderSimulador.upload(imagemRequest.getImagens());
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty() || !produtoOptional.get().getUsuario().equals(usuarioLogado.get()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Produto produto = produtoOptional.get();
        produto.associaImagens(urls);
        produtoRepository.save(produto);
        return ResponseEntity.ok().body("Imagens cadastradas " + "no produto " + produto.getNome() + " com sucesso");
    }
}
