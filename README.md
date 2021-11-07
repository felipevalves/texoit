# Golden Raspberry Awards
Projeto Texo IT

Projeto desenvolvido com SpringBoot e java 17.

Instruções:

Clonar o projeto 

Informar o caminho do csv no arquivo application.properties -> filename.

Caminho padrão na raiz do projeto

![image](https://user-images.githubusercontent.com/61691394/140646189-c688d838-c2b6-40c7-9d02-99461180ce01.png)


mvn package para construir o projeto.

java -jar .\award-0.0.1-SNAPSHOT.jar para executar

Depois de iniciado o projeto é possível acessar o banco de dados no endereço http://localhost:8081/h2-console ( sem senha).

Para acessar o endpoint http://localhost:8081/award/v1/intervals

Para testar o endpoint com o swagger, acessar http://localhost:8081/swagger-ui.htm
