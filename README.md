This Service Functionality

1. Gets resource parent child heirarchy data from database.
2. Saving the resource information in to database.
3. Gets resource data for a particular parent id from database.


Components/Libraries used:
1. Spring AOP - for logging and exception handling.
2. PSQL - as Database
3. Liquibase - for creating schema and default data.
4. Spring JPA - For getting/saving a resource from/to database.
5. Custom annotation to log method params.


Endpoints:

POST http://localhost:8083/add
{
"parent_id" : 5,
"name": "kapil",
"color" : "white"
}

GET http://localhost:8083/getDataById?id=15

GET http://localhost:8083/getAllData



Docker Steps:

docker build -t centime-resource-management .

    
