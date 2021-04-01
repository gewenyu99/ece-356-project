To start the app

First start your mysql instance

``` bash
docker pull mysql
docker run --name ademographics -p 3306:3306 -e MYSQL_ROOT_PASSWORD=rtxon -d mysql
```

Then start your spring boot app

``` bash
./mvnw spring-boot:run
```

To edit mysql stuffs

``` docker
docker exec -it demographics mysql -u root -p
```