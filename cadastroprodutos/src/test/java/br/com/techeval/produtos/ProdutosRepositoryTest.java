package br.com.techeval.produtos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.techeval.produtos.model.Produtos;
import br.com.techeval.produtos.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutosRepositoryTest {
	@Autowired
	private  ProdutoRepository repository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	private Produtos produto = new Produtos("Skol", "Cerveja", "Ambev");
	
	@Test
	public void salvarNovoProduto() {
		this.repository.save(produto);
		assertThat(produto.getId()).isNotNull();
		assertThat(produto.getNome()).isEqualTo("Skol");
		assertThat(produto.getCategoria()).isEqualTo("Cerveja");
		assertThat(produto.getFabricante()).isEqualTo("Ambev");
	}
	
	
	@Test
	public void listarTodosProdutos() {
		List<Produtos> lista = new ArrayList<>(); 
		Iterable<Produtos> produtos =	this.repository.findAll();
		produtos.iterator().forEachRemaining(lista::add);
		
		assertThat(lista.size()).isEqualTo(4);
	
		
	}
	
	
	

	

		
	
	
	
}
