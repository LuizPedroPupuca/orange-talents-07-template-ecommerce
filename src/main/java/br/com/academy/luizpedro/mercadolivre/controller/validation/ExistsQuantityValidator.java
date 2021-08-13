package br.com.academy.luizpedro.mercadolivre.controller.validation;


import br.com.academy.luizpedro.mercadolivre.dto.CompraRequest;
import br.com.academy.luizpedro.mercadolivre.model.Produto;
import br.com.academy.luizpedro.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class ExistsQuantityValidator implements ConstraintValidator<ExistsQuantity, CompraRequest> {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public boolean isValid(CompraRequest compraRequest, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Produto> possivelProduto = produtoRepository.findById(compraRequest.getProdutoId());
        if(possivelProduto.isEmpty()){
            return false;
        }
        Produto produto = possivelProduto.get();
        return produto.getQtde() >= compraRequest.getQuantidade() ? true : false;
    }
}
