package br.com.academy.luizpedro.mercadolivre.utils;


import br.com.academy.luizpedro.mercadolivre.model.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}
