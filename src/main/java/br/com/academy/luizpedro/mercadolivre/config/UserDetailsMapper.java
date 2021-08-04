package br.com.academy.luizpedro.mercadolivre.config;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {
    UserDetails map(Object shouldBeASystemUser);
}
