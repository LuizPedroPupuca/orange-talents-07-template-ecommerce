package br.com.academy.luizpedro.mercadolivre.model;
import br.com.academy.luizpedro.mercadolivre.utils.SenhaLimpa;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.time.LocalDateTime;

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

}
