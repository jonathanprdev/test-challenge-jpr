# Prueba técnica Ecommerce Inditex - Jonathan Patiño Rico:

Esta aplicación está construida de acuerdo con los siguientes requerimientos:


En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

PRICES
-------

BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE           CURR
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR

Campos:

BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
PRICE_LIST: Identificador de la tarifa de precios aplicable.
PRODUCT_ID: Identificador código de producto.
PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
PRICE: precio final de venta.
CURR: iso de la moneda.

Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Se valorará:

- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.

### Notas de diseño:

Para la implementación de este servicio se utilizó una arquitectura hexagonal, teniendo en cuenta sus 3 capas principales:
* Dominio: Es la capa donde están definidas todas las entidades de negocio y que reprensentan el modelo/dominio de negocio.
* Aplicación: Capa encargada de orquestar los casos de uso del sistema y coordinar el dominio. Define los puertos que abstraen la interacción con sistemas externos. En este caso se definió un único puerto de salida para la consulta de tarifas, el cual es implementado por el adaptador de persistencia que accede a la base de datos.
* Infraestructura: En esta capa se definieron 2 adaptadores, uno de entrada que es el controlador web del API y uno de salida que corresponde con la persistencia en H2. Tambien están definidos los diferentes objetos de tranferencia de datos (DTOs y mapper).

### Para ejecutar el servicio como una aplicación Java desde la consola se debe usar el siguiente comando:
* mvn spring-boot:run

la aplicación despliega en el puerto 8080

### Para ver la documentación Swagger del API implementado puede ingresar a la siguiente url:
 - http://localhost:8080/swagger-ui/index.html

### Para acceder a la consola de H2 y conectarse a la base de datos debe usar los siguientes datos:
- http://localhost:8080/h2-console/
  - Driver class: org.h2.Driver
  - jdbc:h2:mem:ecommercedb
  - User Name: sa
  - Password: pwd

### Para ejecutar los test de unitarios se debe ejecutar el comando:
- mvn test

### Para ejecutar los test de integración con los casos propuestos se debe ejecutar el comando:
- mvn verify

### Para ejecutar test e2e con los casos propuestos se debe importar la colección de Postman y correr los test con Run:
- {path del proyecto}/src/main/resources/static/e2e/pricesapp-e2e-postman-collection.json
- Se debe configurar el environment con la variable baseUrl = http://localhost:8080 antes de ejecutar los test

# Despliegue en un contenedor Docker
### 1. Se debe compilar y empaquetar la aplicación usando el siguiente comando:
- mvn clean install

### 2. Construir la imagen de la aplicación ubicarse en la raíz del proyecto y usar el siguiente comando:
- docker-compose build

### 3. Ejecutar la aplicación en el contenedor generado en el paso 2,usar el siguiente comando:
- docker-compose up -d

### la aplicación despliega en el puerto 8080

### Para ver la documentación Swagger del API implementado puede ingresar a la siguiente url:
- http://localhost:8080/swagger-ui/index.html

### En la siguiente ruta puede encontrar una colección de Postman con los 5 casos de prueba propuestos

- {path del proyecto}/src/main/resources/static/Inditex Technical Test Collection.postman_collection.json