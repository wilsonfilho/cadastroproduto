package br.com.techeval.produtos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.techeval.produtos.model.Produtos;
import br.com.techeval.produtos.repository.ProdutoRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutosEndpointTest {
	
	private final int CODIGO_GET_OK = 200;
	private final int CODIGO_CREATE = 201;
	private final int CODIGO_NOT_FOUND = 404;
	
	
	@Autowired
	private TestRestTemplate rest;
	@MockBean
	private ProdutoRepository repository;
	private Produtos produto = new Produtos("Skol", "Cerveja", "Ambev");
	
	@Test
	public void litarTodosProdutos() {
		List<Produtos> listaProdutos = new ArrayList<>();
		listaProdutos.add(new Produtos ("Skol", "Cerveja", "Ambev"));
		listaProdutos.add(new Produtos ("Cajuina", "Refrigerante", "São gerardo"));
		
		BDDMockito.when(repository.findAll()).thenReturn(listaProdutos);
		ResponseEntity<String> response = rest.getForEntity("/produtos", String.class); 
		assertEquals(response.getStatusCodeValue(), CODIGO_GET_OK);
	}

	@Test
	public void getProdutoById() {
		repository.save(produto);
		ResponseEntity<Produtos> response = rest.getForEntity("/produtos/", Produtos.class, repository.findById(produto.getId())); 
		assertEquals(response.getStatusCodeValue(), CODIGO_GET_OK);
	}
	
	@Test
	public void getProdutoComIdInvalido() {
		long id = 99L;
		ResponseEntity<String> response = rest.getForEntity("/produtos/{id}", String.class, id); 
		assertEquals(response.getStatusCodeValue(), CODIGO_NOT_FOUND);
	}

	@Test
	public void salvarProduto() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Produtos> request = new HttpEntity<>(produto, headers);
		ResponseEntity<String> response = rest.postForEntity("/produtos/", request , String.class );
		assertEquals(response.getStatusCodeValue(), CODIGO_CREATE);
	}
	


	
	
}