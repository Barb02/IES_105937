# 3.1

### Notas:
Nessa questão é utilizado o Autowired para instanciar o repositório (userRepository) diretamente no Controller, mas normalmente
isso é feito nos serviços (services) do springboot, que representa a camada de negócio (business layer), onde a lógica do problema é feita.

## c)
### The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?
Por meio da anotação @Autowired do Springboot, que faz a ligação automática (autowire) de relações entre componentes que estão a colaborar.

### List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?
save()
findAll()
findById()
delete()
Na interface CrudRepository, que é extendida pela interface criada UserRepository. O JPA trata de criar implementações para esses métodos.

### Where is the data being saved?
Na instância de UserRepository que foi criada, userRepository.

### Where is the rule for the “not empty” email address defined?
É definida na classe User, ou seja, na camada do domínio.

# 3.2 

## Teste da API:
### createUser
curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"name":"joao","email":"joao@ua.pt"}' \\\
localhost:8080/api/employees

Response: {"id":1,"name":"joao","email":"joao@ua.pt"}

### getAllUsers
curl localhost:8080/api/employees

Response: {"id":1,"name":"joao","email":"joao@ua.pt"}


### getUser
curl localhost:8080/api/employees/1

Response: {"id":1,"name":"joao","email":"joao@ua.pt"}

### updateUser
curl --header "Content-Type: application/json" \\\
--request PUT \\\
--data '{"name":"joao","email":"notjoao@gmail.com"}' \\\
localhost:8080/api/employees/1

Response: {"id":1,"name":"joao","email":"notjoao@gmail.com"}

### deleteUser
curl -X DELETE localhost:8080/api/employees/1

Response: User successfully deleted!

### searchByEmail
(Após a adicionar o user joao novamente, dentre outros):
curl localhost:8080/api/employees?email=joao@ua.pt

Response: \[{"id":1,"name":"joao","email":"joao@ua.pt"}]


# 3.3
## Interações com a API:

### addMovie
curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"title":"Bottoms","year":2022}' \\\
localhost:8080/api/movie

Response: {"id":1,"title":"Bottoms","year":2022}

curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"title":"D.E.B.S","year":2004}' \\\
localhost:8080/api/movie

Response: {"id":2,"title":"D.E.B.S","year":2004}

### addQuote
curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"quote":"All women are hot to me","movie":1}' \\\
localhost:8080/api/quote

Response: {"id":1,"quote":"All women are hot to me","movie":1}

curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"quote":"Lucy Diamond.","movie":2}' \\\
localhost:8080/api/quote

Response: {"id":2,"quote":"Lucy Diamond.","movie":2}

curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"quote":"What the fuuck, these girls are ugly.","movie":1}' \\\
localhost:8080/api/quote

Response: {"id":3,"quote":"What the fuuck, these girls are ugly.","movie":1}

curl --header "Content-Type: application/json" \\\
--request POST \\\
--data '{"quote":"Hello world","movie":3}' \\\
localhost:8080/api/quote

Response: {"timestamp":"2023-11-03T19:34:35.889+00:00","status":400,"error":"Bad Request","trace":"org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST ... "message":"Movie doesn't exist","path":"/api/quote"}

### getMovies
curl localhost:8080/api/movies

Response: \[{"id":1,"title":"Bottoms","year":2022},{"id":2,"title":"D.E.B.S","year":2004}]

### getQuote
curl localhost:8080/api/quote

Response: {"id":2,"quote":"Lucy Diamond.","movie":2}

curl localhost:8080/api/quote

Response: {"id":3,"quote":"What the fuuck, these girls are ugly.","movie":1}

### getQuotesByMovie
curl localhost:8080/api/quotes?movie=1

Response: \[{"id":1,"quote":"All women are hot to me","movie":1},{"id":3,"quote":"What the fuuck, these girls are ugly.","movie":1}]
