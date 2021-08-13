package br.com.academy.luizpedro.mercadolivre.utils;
import br.com.academy.luizpedro.mercadolivre.model.Compra;
import br.com.academy.luizpedro.mercadolivre.model.Transacao;


public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}
