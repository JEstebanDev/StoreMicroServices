# StoreMicroServices
Real-time store microservices project practicing and learning all the process to create a full backend project including metrics.

## Technologies stack
* Metrics: Prometheus Grafana
* Distributed tracing: Jaeger
* Distributed streaming: Kafka
* Circuit Breaker: Resilience4j, WebFlux
* Security: KeyCloak
* Apigateway: spring cloud starter gateway
* Communicate with each with spring cloud netflix eureka
* Databases: Postgresql, Mysql, MongoDB
* Testing: testcontainers, junit-jupiter

<img src="https://github.com/JEstebanDev/StoreMicroServices/blob/main/version-images/Final.jpg" alt="architecture design" title="Architecture Design">

## To start the project do not forget to have:
* Docker with postgresql, mysql, mongo(or web link), keycloak, jaeger, for the last version don't forget to start those containers, and create the databases
* For docker ports don't forget to add the next line in your hosts file (C:\Windows\System32\drivers\etc): 127.0.0.1 keycloak

### Local Ports
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/13987920-8b6617b8-f3bd-471b-9e2d-e12e7b8302df?action=collection%2Ffork&collection-url=entityId%3D13987920-8b6617b8-f3bd-471b-9e2d-e12e7b8302df%26entityType%3Dcollection%26workspaceId%3D62a48e0e-a077-4bb9-aa40-d5a1d4b86cc3)

### Docker Ports
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/13987920-c10316c3-71b5-4671-8753-b7942d2b87ba?action=collection%2Ffork&collection-url=entityId%3D13987920-c10316c3-71b5-4671-8753-b7942d2b87ba%26entityType%3Dcollection%26workspaceId%3D62a48e0e-a077-4bb9-aa40-d5a1d4b86cc3)

## Author
Esteban Castaño: <castanoesteban9@gmail.com>
