package br.com.academy.luizpedro.mercadolivre.dto;
import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import br.com.academy.luizpedro.mercadolivre.utils.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class UsuarioRequest {

    @NotBlank @Email
    private String login;

    @NotBlank @Length(min = 6)
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login, new SenhaLimpa(senha));
    }
}
