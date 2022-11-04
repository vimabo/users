# UsersBackend
Proyecto en Maven + Spring Boot + JPA para la administración de usuarios.

# Instrucciones para construir el proyecto
a. El proyecto funciona con JDK versión 1.8.

b. clonar el proyecto y abrirlo en el ide de desarrollo.

c. Compilación: mvn instalación limpia.

d. Metodo GET para listar todos los usuarios en BD.
http://localhost:8080/api/1.0/users/list

e. Metodo GET para obtener un usuario por parametro email en BD.
http://localhost:8080/api/1.0/users/findByEmail/{email}

d. Metodo POST para crear un usuario en BD.
http://localhost:8080/api/1.0/users/create

REQUEST:

{
   "name":"Jose Manuel",
   
   "username":"joselillo",
   
   "email":"56456@gmail.com",
   
   "phone":"45664"
}

d. Metodo DELETE para borrar un usuario en BD.
http://localhost:8080/api/1.0/users/delete/{id}

d. Metodo POST para consultar una api externa.
http://localhost:8080/api/1.0/users/externa?param=1-9


# SWAGGER
http://localhost:8080/swagger-ui.html#/


# SQL
BD MYSQL= se genera automaticamente la BD si no existe y la tabla user cuando se despliega el proyecto.

URL de JDCB: jdbc:mysql://localhost:3306/gest_user?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=UTC

Usuario: root

Contraseña: root

# AUTHORIZATION SERVICES
Parametros se encuentran configurados en el properties del proyecto.

spring.authorization.user=user

spring.authorization.password=pass
