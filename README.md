
# Calculadora de rotas mais barata - API
API da calculadora de rota mais barato feita com Java e Spring Boot.

# Como executar a aplicação  
 	-Certifique-se de que a porta local 8080 não esta sendo usada por outro programa e tenha o java 8 ou superior instalado.   
  	-via linha de comando acesse a pasta onde você baixou o jar enviado por e-mail  
  	-coloque o input-routes.csv na mesma pasta do arquivo jar  
  	-execute via linha de comando: java -jar calculadoraRota.jar input-routes.csv    
  	  
# Explique as decisões de design adotadas para a solução
  	- Foi usado o algoritmo de Dijkstra para achar o caminho com o melhor custo. Essa estrutura de dados foi criada para resolver o tipo de problema em questão.  
  	- Foi usado o Spring boot, pois, ele já tem um tomcat embutido, assim fica fácil executar o programa e disponibilizar a rest API.
  	   
# Descreva sua API Rest de forma simplificada  
  	
  	get	/rota/{DE-PARA}  	
  	-retorna a rota mais barata de acordo com os pontos informados
  	-formato aceito DE-PARA. Exemplo BRC-CDG
  	
  	post /rota/file
  	-adiciona mais rotas às rotas existente, caso a rota já exista o custo da rota é atualizado.
  	-aceita um arquivo CSV
  	-retorna a nova lista de rota cadastradas
  	
  	Uma forma de ver a documentação e testar a API é acessando a página do swagger:  
  	-Quando o programa estiver sendo executado acesse na máquina local a documentação no link: http://localhost:8080/swagger-ui.html 
  	