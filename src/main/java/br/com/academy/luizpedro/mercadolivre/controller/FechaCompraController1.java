package br.com.academy.luizpedro.mercadolivre.controller;
import br.com.academy.luizpedro.mercadolivre.dto.NovaCompraRequest;
import br.com.academy.luizpedro.mercadolivre.model.Compra;
import br.com.academy.luizpedro.mercadolivre.model.GatewayPagamento;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.repository.CompraRepository;
import br.com.academy.luizpedro.mercadolivre.repository.ProdutoRepository;
import br.com.academy.luizpedro.mercadolivre.repository.UsuarioRepository;
import br.com.academy.luizpedro.mercadolivre.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class FechaCompraController1 {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping("/compra")
    @Transactional
    public String casdastraCompra(@RequestBody @Valid NovaCompraRequest request,
                                         @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = produtoRepository.findById(request.getIdProduto()).get();
        int qtde = request.getQtde();
        boolean abateu = produto.abateNoEstoque(request.getQtde());
        if (abateu){
            Usuario comprador = usuarioRepository.findByLogin(usuarioLogado.getUsername()).get();
            GatewayPagamento gateway = request.getGateway();
            Compra compra = new Compra(produto, qtde, comprador, request.getGateway());
            compraRepository.save(compra);
            if(gateway.equals(GatewayPagamento.pagseguro)){
                String urlRetornoPagseguro = uriComponentsBuilder.path("retorno-pagseguro/{id}")
                        .buildAndExpand(compra.getId()).toString();
                return "pagseguro.com" + compra.getId()
                        +"?redirectUrl="+urlRetornoPagseguro;
            }else {

                String urlRetornoPaypal = uriComponentsBuilder.path("retorno-paypal/{id}")
                        .buildAndExpand(compra.getId()).toString();
                return "paypal.com" + compra.getId()
                        +"?redirectUrl="+urlRetornoPaypal;

            }
        }

        BindException bindException = new BindException(request, "novaCompraRequest");
        bindException.reject(null, "Não foi possível realizar a compra!!!");
        throw bindException;
    }
}
