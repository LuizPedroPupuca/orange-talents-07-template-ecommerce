package br.com.academy.luizpedro.mercadolivre.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String encode(){
        return new BCryptPasswordEncoder().encode(senha);
    }

}
