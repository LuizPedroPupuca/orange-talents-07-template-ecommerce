package br.com.academy.luizpedro.mercadolivre.config;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.utils.UsuarioLogado;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{
    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario)shouldBeASystemUser);
    }
}
