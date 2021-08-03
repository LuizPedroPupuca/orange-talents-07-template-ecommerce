package br.com.academy.luizpedro.mercadolivre.repository;

import br.com.academy.luizpedro.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
