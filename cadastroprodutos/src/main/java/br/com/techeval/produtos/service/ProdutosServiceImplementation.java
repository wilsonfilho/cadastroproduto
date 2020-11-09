package br.com.techeval.produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.techeval.produtos.model.Produtos;
import br.com.techeval.produtos.repository.ProdutoRepository;
import javassist.NotFoundException;

@Service
public class ProdutosServiceImplementation implements ProdutosService{
	@Autowired
	private ProdutoRepository repository;
	
	@Override
	public Produtos salvarProduto(Produtos product) {
		Produtos Produtos = repository.save(product);
		return Produtos;
	}

	@Override
	public Object listarTodosProdutos(Pageable page) {
		return repository.findAll(page);
	}
	
	@Override
	public Produtos buscarProdutoById(Long id) throws NotFoundException{
		Produtos Produtos = idExists(id);
		return Produtos;
	}
	
	
	private Produtos idExists(Long id) throws NotFoundException  {
		Produtos Produtos = repository.findById(id).orElse(null);
		if(Produtos == null) {
			throw new NotFoundException("Não foi possível encontrar o produto com id: " + id);
		}
		return Produtos;
	}
}
