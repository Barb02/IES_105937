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
