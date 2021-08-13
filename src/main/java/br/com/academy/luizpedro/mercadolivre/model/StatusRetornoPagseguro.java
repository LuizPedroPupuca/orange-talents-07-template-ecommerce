package br.com.academy.luizpedro.mercadolivre.model;

public enum StatusRetornoPagseguro {

    SUCESSO,ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.sucesso;
        }

        return StatusTransacao.erro;
    }
}
