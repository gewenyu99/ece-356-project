To start the app

First start your mysql instance
``` bash
run --name demographics -p 3306:3306 -e MYSQL_ROOT_PASSWORD=rtxon -d mysql
```

Then start your spring boot app

``` bash
./mvnw spring-boot:run
```