
# Calculadora de rotas mais barata - API
API da calculadora de rota mais barato feita com Java e Spring Boot.

  * Como executar a aplicação  
 	-Certifiquece que a porta local 8080 não esta sendo usada por outro programa  
  	-acesse a pasta onde você baixou o jar enviado por e-mail
  	-coloque o input-routes.csv na mesma pasta do arquivo jar
  	-execute: java -jar calculadoraRota.jar input-routes.csv  
  
  * Estrutura dos arquivos/pacotes
  	-Repositório git
  
  * Explique as decisões de design adotadas para a solução
  	- Foi usado o algoritmo de Dijkstra para achar o caminho com o melhor custo. Essa estrutura de dados foi criada para resolver esse tipo de problema em questão.
  	- Foi usado o Spring boot, pois, ele já tem um tomcat embutido, assim fica fácil executar o programa e disponibilizar a rest API.
  	   
  * Descreva sua API Rest de forma simplificada  
  	Quando o programa estiver sendo executado acesse na máquina local a documentação no link: http://localhost:8080/swagger-ui.html  