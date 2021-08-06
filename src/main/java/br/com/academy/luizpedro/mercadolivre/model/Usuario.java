package br.com.academy.luizpedro.mercadolivre.model;
import br.com.academy.luizpedro.mercadolivre.utils.SenhaLimpa;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime  dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Deprecated
    public Usuario(){ }

    public Usuario(String login, SenhaLimpa senhalimpa) {
        this.login = login;
        this.senha = senhalimpa.encode() ;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
