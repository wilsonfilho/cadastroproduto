# cadastroproduto
Projeto com exemplo de consulta e cadastro de produtos utlizando:  Spring Boot; Api Rest; JPA; H2 Database; Habbitmq;


Para salvar e visualizar a mensagens na fila será necessário instalar o gerenciador de filas rabbitmq-server-3.8.9 e inicializar o mesmo.

Foi exposta uma Api que:

> Lista todos os produtos;
> Lista um produto por id;
> Insere um novo produto;
> Salva as mensagens na fila

Obs: O projeto ao iniciar já insere 3 produtos na base H2.


