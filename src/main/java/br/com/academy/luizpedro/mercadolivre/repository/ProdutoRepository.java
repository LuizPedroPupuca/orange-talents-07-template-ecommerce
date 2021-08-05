package br.com.academy.luizpedro.mercadolivre.repository;

import br.com.academy.luizpedro.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
