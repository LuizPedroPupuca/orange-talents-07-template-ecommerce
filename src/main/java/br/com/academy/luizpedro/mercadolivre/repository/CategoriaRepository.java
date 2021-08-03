package br.com.academy.luizpedro.mercadolivre.repository;

import br.com.academy.luizpedro.mercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
