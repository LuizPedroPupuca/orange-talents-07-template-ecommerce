package br.com.academy.luizpedro.mercadolivre.controller;

import br.com.academy.luizpedro.mercadolivre.dto.NovaCompraNFRequest;
import br.com.academy.luizpedro.mercadolivre.dto.RankingNovaCompraRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class NotaFiscalRankingController {
    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("criando nota "+request);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("criando ranking"+request);
        Thread.sleep(150);
    }
}
