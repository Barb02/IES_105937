## Servlet

### Definição:
A Servlet is a Java class that runs at the server, handles (client) requests, 
processes them, and reply with a response.

Servlet Containers provide a runtime to execute server-side web-related Java code

## Funcionamento do Tomcat

É um webserver que redireciona os pedidos para o lugar certo, evitando assim que o servidor fique sobrecarregado. Quem redireciona os pedidos é o war 

## Diferenças na 2.2 Docker x Docker-compose

"Instead of copying the WAR artifact to the container, Docker Compose bind mounts the application output directory ./target to the Tomcat directory in the container: /usr/local/tomcat/webapps."

Ou seja, no Docker o ficheiro war é copiado para o diretório do Tomcat no container, mas no Docker-compose
o diretório target é mapeado para o mesmo. 

No Docker é necessário recriar a imagem a cada novo update, já no compose, não, por causa dos volumes.

### OBS: 
Com o docker-compose, para as mudanças terem efeito é necessário fazer down, mvn package, e up. Com o docker
é remover o container e a imagem, mvn package, build e run.

## 2.3 - Correr SpringBoot Webapp
Usar o comando: mvn install -DskipTests && java -jar target/webapp-0.0.1-SNAPSHOT.jar 
(barra no guião ta invertida)
ou
/mvnw spring-boot:run

