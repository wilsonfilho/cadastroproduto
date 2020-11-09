package br.com.techeval.produtos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.techeval.produtos.model.Produtos;



@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produtos, Long> {

}
