# neoris
Repositorio para la prueba de Microservicios Intermedio en Neoris
Luego de clonar el repositorio ubicarse en la carpeta del proyecto y ejecutar:
docker build -t test-neoris:v1 .
Para construir la imagen Docker
Luego ubicarse en la carpeta docker-compose y ejecutar el comando
docker-compose up o docker-compose up -d(ejecución en segun plano)
Si al ejecutarse no se insertan los valores iniciales 
de genero y tipo de cuentas por favor ejecutar el archivo data.sql que
se encuentra en la carperta resources del proyecto.