# SincronizacaoReceita
Aplicação desenvolvida com springboot para fazer upload de arquivos em CSV em banco de dados SQL e
download do banco de dados em um novo arquivo CSV atualizado com os dados inseridos.

Aplicação desenvolvida com Springboot 2.4.4, jdk 1.8 e MySQL conexão jdbc.

criar schema no MySQL com nome "receitadb" ou utilizar outro de sua preferência. Se utilizar outro, realizar a mudança do nome do db.

#application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/receitadb

spring.datasource.username=root

spring.datasource.password=admin

spring.jpa.generate-ddl=true

Após rodar a aplicação como springboot app no IDE, 
a aplicação estará disponível na URL http://localhost:8080/index.html
