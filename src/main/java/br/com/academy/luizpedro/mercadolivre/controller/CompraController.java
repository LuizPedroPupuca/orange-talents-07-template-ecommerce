package br.com.academy.luizpedro.mercadolivre.controller;


import br.com.academy.luizpedro.mercadolivre.dto.CompraRequest;
import br.com.academy.luizpedro.mercadolivre.dto.RetornoPagseguroRequest;
import br.com.academy.luizpedro.mercadolivre.dto.RetornoPaypalRequest;
import br.com.academy.luizpedro.mercadolivre.model.Compra;
import br.com.academy.luizpedro.mercadolivre.model.GatewayPagamento;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.repository.CompraRepository;
import br.com.academy.luizpedro.mercadolivre.repository.ProdutoRepository;
import br.com.academy.luizpedro.mercadolivre.utils.DisparadorDeEmail;
import br.com.academy.luizpedro.mercadolivre.utils.EventosNovaCompra;
import br.com.academy.luizpedro.mercadolivre.utils.RetornoGatewayPagamento;
import br.com.academy.luizpedro.mercadolivre.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CompraController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private DisparadorDeEmail disparadorDeEmail;
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping(value = "/api/compras")
    @Transactional
    public String compraProdutoParte01(@RequestBody @Valid CompraRequest request,
                                       @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produtoASerComprado = manager.find(Produto.class,
                request.getProdutoId());
        int quantidade = request.getQuantidade();
        boolean abateu = produtoASerComprado.abataEstoque(quantidade);

        if (abateu) {
            GatewayPagamento gateway = request.getGatewayPagamento();
            Compra novaCompra = new Compra(produtoASerComprado, quantidade,
                    usuarioLogado.get(), gateway);
            manager.persist(novaCompra);
            disparadorDeEmail.enviaEmail(produtoASerComprado.getUsuario());

            return novaCompra.urlRedirecionamento(uriComponentsBuilder);

        }

        BindException problemaComEstoque = new BindException(request,
                "novaCompraRequest");
        problemaComEstoque.reject(null,
                "Não foi possível realizar a compra por conta do estoque");

        throw problemaComEstoque;
    }

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    //1
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
        return processa(idCompra, request);
    }

    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);
        eventosNovaCompra.processa(compra);

        return compra.toString();
    }
}