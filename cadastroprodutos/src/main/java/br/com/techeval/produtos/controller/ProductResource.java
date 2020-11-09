package br.com.techeval.produtos.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.techeval.produtos.model.Produtos;
import br.com.techeval.produtos.service.ProdutosService;
import br.com.techeval.produtos.service.SenderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/produtos", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
public class ProductResource {
		
	@Autowired
	ProdutosService service;
	
	@Autowired
    SenderService senderService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Efetua o cadastro de um produto")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Produto Criado com Sucesso!")})
	@ApiImplicitParams({@ApiImplicitParam(name = "produto", value = "Estrutura para criação de produto.", dataType = "Cadastrar Produto", required = true)})
	
	public ResponseEntity<Produtos> postProduct(@Valid @RequestBody Produtos produto) {
		Produtos produtoSave = service.salvarProduto(produto);
		 // Send log via RabittMQ to the log server
        senderService.sendAdded(produtoSave);
		return new ResponseEntity<>(produtoSave, HttpStatus.CREATED);
	}

	@GetMapping
	@ApiOperation(value = "Lista os porodutos cadastrados")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", defaultValue = "0",  dataType = "int",   value = "número da página"),
		@ApiImplicitParam(name = "limit",defaultValue = "5",  dataType = "int",   value = "quantidade de itens retornados"),
		@ApiImplicitParam(name = "field",defaultValue = "id", dataType = "String",value = "campo para ordenação"),
		@ApiImplicitParam(name = "sort", defaultValue = "asc",dataType = "String",value = "ordenação dos itens")
	})

	public ResponseEntity<Object> getAllProdutos(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit",defaultValue = "5") int limit,
			@RequestParam(value = "field", defaultValue = "id") String field,
			@RequestParam(value = "sort", defaultValue = "asc") String order){
		
		return ResponseEntity.ok(service.listarTodosProdutos(PageRequest.of(page, limit, Direction.fromString(order), field)));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna o poroduto com o 'id' informado")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Produto Não Encontrado!")})
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "Identificador do produto", dataType = "Long", required = true)})

	public ResponseEntity<Produtos> getProductById(@PathVariable Long id) throws NotFoundException {
		Produtos Produtos = service.buscarProdutoById(id);
		return new ResponseEntity<>(Produtos, HttpStatus.OK);
	}

	

	
}
