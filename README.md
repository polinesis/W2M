# W2M
ADMINISTRACION DE HEROES
JAVA 11, SPRINGBOOT, MAVEN

La app inicia : http://localhost:8080/
Test de sericio activo: http://localhost:8080/healthz
Documentacion de la api: http://localhost:8080/v2/api-docs

A- la api tiene seguridad, por un tokenizer que recibe usuario y pas  es un post : http://localhost:8080/autenticar,  body: {
    "username": "W2M",
	"password": "W2M124"
}

con el token se lo pasa por header por parametro con el nombre  :  x-api-key
B- la app utiliza liquibase, para la carga de script de base de datos. Se utiliza almacenamiento en memoria H2.http://localhost:8080/h2-console/ .
C- en la seccion test, hay un test de servicio  y otro integral del controller mas capa de servicio.
