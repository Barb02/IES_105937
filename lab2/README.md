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
./mvnw spring-boot:run

## 2.4 - Explicação da implementação

### Dados estáticos em ficheiro
Optei por guardar as quotes com seu determinado show em um ficheiro para tornar a implementação mais genérica
e adaptável. Para adicioná-los nas estruturas de dados, criei a função addData(), que é chamada apenas quando
se verifica que as estruturas estão vazias. 

### Estruturas de dados
Alterei as estruturas de dados diversas vezes enquanto construia a solução, por perceber que certa operação
seria facilitada se as estruturas fossem diferentes. Inicialmente estava a utilizar HashMaps onde guardava ids
e strings diretamente, mas depois optei por guardar os objetos das classes Quote e Show, já que assim seria
mais fácil retornar em JSON, assim como foi feito no exercício 2.3. Assim, criei três estruturas: um HashMap 
de quotes (ArrayList) por show (String), um ArrayList de shows e um ArrayList de quotes. 

Porém, essa mudança trouxe problemas no endpoint api/quotes?show=id ao não permitir que a indexação fosse feita diretamente pelo ID, já que as keys eram objetos. Isso me obrigou a percorrer todos os shows e comparar o id de
cada um com o ID enviado como parâmetro, o que teria grande impacto caso o número de shows fosse muito alto. Ainda tentei alterar a key do HashMap para que fosse feita diretamente com o long, mas isso impediria a lógica
do containsKey utilizada na inserção para garantir que um mesmo show não fosse adicionado duas vezes.

Portanto, esta implementação tem perdas e ganhos. Os ganhos foram uma maior simplicidade na lógica dos endpoints, além da eficiência nos endpoints api/quote e api/shows. 

### Tratamento de erros
Para tratar dos erros utilizei o ResponseStatusException da springframework.
No caso em que o erro surgiria a partir de um mau processamento do servidor, utilizei o código 500, de internal
server error. Já no caso de um erro gerado por um id existente, no caso do endpoint api/quotes?show=id, utilizei
o erro 404.