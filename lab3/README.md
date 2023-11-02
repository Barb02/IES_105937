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
curl --header "Content-Type: application/json" \
--request POST \
--data '{"name":"joao","email":"joao@ua.pt"}' \
localhost:8080/api/employees

Response: {"id":1,"name":"joao","email":"joao@ua.pt"}

### getAllUsers
curl localhost:8080/api/employees

Response: {"id":1,"name":"joao","email":"joao@ua.pt"}


### getUser
curl localhost:8080/api/employees/1

Response: {"id":1,"name":"joao","email":"joao@ua.pt"}

### updateUser
curl --header "Content-Type: application/json" \
--request PUT \
--data '{"name":"joao","email":"notjoao@gmail.com"}' \
localhost:8080/api/employees/1

Response: {"id":1,"name":"joao","email":"notjoao@gmail.com"}

### deleteUser
curl -X DELETE localhost:8080/api/employees/1

Response: User successfully deleted!
