package br.com.academy.luizpedro.mercadolivre.utils;

import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DisparadorDeEmail {
    public void enviaEmail(Usuario usuario){
        System.out.println("Email enviado para "+ usuario.getLogin());
    }
}
