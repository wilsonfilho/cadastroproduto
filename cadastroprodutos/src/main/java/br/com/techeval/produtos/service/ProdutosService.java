package br.com.techeval.produtos.service;

import org.springframework.data.domain.Pageable;

import br.com.techeval.produtos.model.Produtos;
import javassist.NotFoundException;

public interface ProdutosService {
	
	public Produtos salvarProduto(Produtos product);
	
	public Object listarTodosProdutos(Pageable page);
	
	public Produtos buscarProdutoById(Long id) throws NotFoundException ;
	
}
