# customer_api

Software Requirement:
```
Java 11+
Maven 3
MySQL
```

## Installation
##### Get the project
Get the project from this Github Repository into your local machine by execute:
```
git clone https://github.com/kmantanoo/customer_api.git
```
You may need to authenticate to Github before git will clone the project. 
Once the project is on your local machine, edit file `application.properties` located at
`customer_api/src/main/resources` to change some MySQL parameters e.g. username, password.
The username to be used should have the access for manipulating database, table. 

##### Building JAR
When you ready to build the JAR
You will run maven on the directory which contains pom.xml like `customer_api` directory
```
mvn clean package spring-boot:repackage
```
Maven will clean, package and repackage for Spring Boot Application into JAR format under the `target` directory

##### Usage
When you need to run the application just execute this command on terminal
```
java -jar target/customer_api-${version}.jar
```
If you haven't change any JDBC URL, when you run the application, it'll auto create the database name `customerapi` and associated tables e.g. `customers`
in that database.


## Trying to send request
This application has 2 endpoints
```
1. /register for register new customer
2. /customer for retrieving customer information
```

and the usage flow is in this order
1. Call POST /register to register new customer, each request will be validated before it will be save to the database,
if validation fail by sending salary less than 15000 or phone not starts with '0' and followed by 9 digits the API will response in `HTTP Code 400`. 
If validation passes, it will try to save customer information to the database and since we configured unique constraint on column `username` for table `customers`
, the API may results in `HTTP Code 409` tell the caller that the it alreader has this username. 
If register was successful the API will return a JWT associated with user back to the caller for further accessing.

example POST command:
```
curl -k -X POST -H "Content-Type: application/json" -d '{"username": "tommysh","password": "asldkfj","address": "Birmingham","phone": "0812345678","salary": 15000}' https://localhost/customer
```

2. Call GET /customer/{id} to retrieve customer information by using JWT from register to access the API endpoint. This will return information of the customer
whos id equal to `{id}`

example GET command:
```
curl -k -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teXNoIiwiZXhwIjoxNjM3NTAyMTQyLCJpYXQiOjE2Mzc1MDE4NDJ9.CIREs2avK8MfOhFb9hOcv_9g9RLOeGoGzXLMH9eTwkI" https://localhost/customer/2
```

## API Specification
##### Register
* **URL**

  /register

* **Method:**

  `POST`
  
*  **URL Params**

* **Data Params**

   **Required:**
 
   `username=[string]`
   
   `password=[string]`
   
   `address=[string`
   
   `phone=[string]`
   
   `salary=[long]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ id : 12, accessToken : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21teXNoIiwiZXhwIjoxNjM3NTAyMTQyLCJpYXQiOjE2Mzc1MDE4NDJ9.CIREs2avK8MfOhFb9hOcv_9g9RLOeGoGzXLMH9eTwkI" }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "salary must greater than 15000" }`

  OR

  * **Code:** 409 CONFLICT <br />
    **Content:** `{ error : "Duplicate username tommysh" }`
    
    
##### CustomerInfo
* **URL**

  /customer/{id}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
   
   `id=[long]`

* **Data Params**
   None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{
    "id": 1,
    "username": "tommysh",
    "address": "Birmingham",
    "phone": "0812345678",
    "refCode": "202111225678",
    "memberType": "Silver",
    "salary": 15000
}`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `Could not find customer with id=2`

