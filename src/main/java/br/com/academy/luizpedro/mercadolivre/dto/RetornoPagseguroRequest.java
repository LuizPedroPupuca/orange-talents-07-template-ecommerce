package br.com.academy.luizpedro.mercadolivre.dto;


import br.com.academy.luizpedro.mercadolivre.model.Compra;
import br.com.academy.luizpedro.mercadolivre.model.StatusRetornoPagseguro;
import br.com.academy.luizpedro.mercadolivre.model.Transacao;
import br.com.academy.luizpedro.mercadolivre.utils.RetornoGatewayPagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {
    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagseguroRequest(@NotBlank String idTransacao,
                                   StatusRetornoPagseguro status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest [idTransacao=" + idTransacao
                + ", status=" + status + "]";
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao,compra);
    }

}
