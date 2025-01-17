package br.com.academy.luizpedro.mercadolivre.controller;
import br.com.academy.luizpedro.mercadolivre.dto.*;
import br.com.academy.luizpedro.mercadolivre.model.Opiniao;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
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

    @PostMapping("/opiniao")
    @Transactional
    public ResponseEntity cadastraOpiniao(@RequestBody@Valid OpiniaoRequest opiniaoRequest,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(opiniaoRequest.getIdProduto());

        if (produtoOptional.isEmpty() || !produtoOptional.get().getUsuario().equals(usuarioLogado.get()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Produto produto = produtoOptional.get();
        Usuario usuario = usuarioLogado.get();
        produto.adicionaOpiniao(opiniaoRequest, produto, usuario);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pergunta")
    @Transactional
    public void cadastraPergunta(@RequestBody@Valid PerguntaRequest perguntaRequest,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(perguntaRequest.getIdProduto());
        Produto produto = produtoOptional.get();
        Usuario usuario = usuarioLogado.get();
        produto.adicionaPergunta(perguntaRequest, produto, usuario);
        produtoRepository.save(produto);
        System.out.println("Pergunta: "+perguntaRequest.getTitulo()+" para " +usuario.getLogin());

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalhesResponse> detalhaProduto(@PathVariable Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(new ProdutoDetalhesResponse(produto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
