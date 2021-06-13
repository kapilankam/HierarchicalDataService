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
6. Deployed services using docker compose.

Endpoints:

POST http://ec2-15-206-210-171.ap-south-1.compute.amazonaws.com:8083/add
{
"parent_id" : 5,
"name": "kapil",    
"color" : "white"
}

GET http://ec2-15-206-210-171.ap-south-1.compute.amazonaws.com:8083/getDataById?id=1
GET http://ec2-15-206-210-171.ap-south-1.compute.amazonaws.com:8083/getAllData



Docker Steps:
1. Created two docker images one for service and another is for database.
2. Placed the service docker image in docker hub.
3. Deployed the service in AWS using docker-compose.yml

docker build -t kapilankam/centime-resource-management-updated:latest .

docker push kapilankam/centime-resource-management-updated:latest

docker-compose up
    
