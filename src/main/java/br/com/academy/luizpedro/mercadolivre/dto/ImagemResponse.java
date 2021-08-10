package br.com.academy.luizpedro.mercadolivre.dto;

import br.com.academy.luizpedro.mercadolivre.model.Imagem;

public class ImagemResponse {

    private String url;

    ImagemResponse(Imagem imagem){
        this.url = imagem.getUrl();
    }

    public String getUrl() {
        return url;
    }
}
