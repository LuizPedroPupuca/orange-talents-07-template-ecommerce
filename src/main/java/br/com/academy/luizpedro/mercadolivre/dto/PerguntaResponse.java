package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.model.Pergunta;

public class PerguntaResponse {

    private String titulo;


    public PerguntaResponse(Pergunta pergunta){
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
