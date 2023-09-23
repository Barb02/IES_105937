# Maven

https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

# Criar projeto

mvn archetype:generate -DgroupId=com.pt.ua -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


# mvn package: reune dependencias, compila e cria jar

Não é necessário fazer mvn package após mudanças no código

# mvn exec

mvn exec:java -Dexec.mainClass="com.pt.ua.WeatherStarter" -Dexec.args="1010500" 

OBS: No Windows usar aspas:  mvn exec:java -D"exec.mainClass"="com.pt.ua.WeatherStarter" -D"exec.args"="1010500" 

# Docker

## docker build: cria imagem
-t para dar nome à imagem
Sintaxe: docker build -t nome-da-imagem diretrio-dockerfile
normalmente para o úlimto usar "." (diretório atual)

## docker run: inicia container
Sintaxe: docker run -dp 127.0.0.1:3000:3000 getting-started

The -d flag (short for --detach) runs the container in the background. The -p flag (short for --publish) creates a port mapping between the host and the container. The -p flag takes a string value in the format of HOST:CONTAINER, where HOST is the address on the host, and CONTAINER is the port on the container. The command publishes the container's port 3000 to 127.0.0.1:3000 (localhost:3000) on the host. Without the port mapping, you wouldn't be able to access the application from the host.

## docker ps: lista containers

## docker images: lista imagens

## docker stop: para container
Sintaxe: docker stop container-id

## docker rm: remove container
Sintaxe: docker rm container-id
OU: docker rm -f container-id (força o stop)