"OrderBoard" is a SpringBoot project, which can be easily deploy in any of Micro Service Environment.

There is runtime DERBY database used to store/read data/orders. No need to install Database separately. 

Also there is no external Tomcat server required to run this SpringBoot application.   

Spring Data JPA used to perform CRUD operation.

Exception handling implemented by using Spring Controller Advice. All the exceptions from controller layer terminates here. 

User defined error response is being sent to client.

"# OrderBoard" 

step 1: check post in "application.properties" file and change if required

step 2: 
  run command : mvn clean install

step 3: 
  after successful compile, go to "target" folder and run "java -jar JAR_FILE_NAME""

Following are REST API details: 

-----------------------------------------------------------------------
To get all orders

URL : http://localhost:8080/getallorders

RequestType = GET

Response:
[
    {
        "id": 1,
        "userid": "user1",
        "orderQuantity": 3.5,
        "pricePerKg": 306,
        "orderType": "SELL"
    },
    {
        "id": 6,
        "userid": "user6",
        "orderQuantity": 4,
        "pricePerKg": 307,
        "orderType": "BUY"
    },
    {
        "id": 2,
        "userid": "user2",
        "orderQuantity": 1.2,
        "pricePerKg": 310,
        "orderType": "SELL"
    },
    {
        "id": 5,
        "userid": "user5",
        "orderQuantity": 4,
        "pricePerKg": 306,
        "orderType": "BUY"
    },
    {
        "id": 3,
        "userid": "user3",
        "orderQuantity": 1.5,
        "pricePerKg": 307,
        "orderType": "SELL"
    },
    {
        "id": 4,
        "userid": "user4",
        "orderQuantity": 2,
        "pricePerKg": 306,
        "orderType": "SELL"
    }
]
-----------------------------------------------------------------------

To create order

URL : http://localhost:8080/order/add

RequestType = POST

JSON to create Order:
[
{    
	"id": 1,   
	"userid": "user1",
	"orderQuantity": 3.5,
	"pricePerKg": 306,
	"orderType": "SELL"
},
{    
	"id": 6,   
	"userid": "user6",
	"orderQuantity": 4.0,
	"pricePerKg": 307,
	"orderType": "BUY"
},
{    
	"id": 2,   
	"userid": "user2",
	"orderQuantity": 1.2,
	"pricePerKg": 310,
	"orderType": "SELL"
},
{    
	"id": 5,   
	"userid": "user5",
	"orderQuantity": 4.0,
	"pricePerKg": 306,
	"orderType": "BUY"
},
{    
	"id": 3,   
	"userid": "user3",
	"orderQuantity": 1.5,
	"pricePerKg": 307,
	"orderType": "SELL"
},
{    
	"id": 4,   
	"userid": "user4",
	"orderQuantity": 2.0,
	"pricePerKg": 306,
	"orderType": "SELL"
}
]

Response: Provide Request Headers to get details of created orders
[
    {
        "headers": {
            "Location": [
                "http://localhost:8080/getorder/1"
            ]
        },
        "body": null,
        "statusCode": "CREATED",
        "statusCodeValue": 201
    },
    {
        "headers": {
            "Location": [
                "http://localhost:8080/getorder/6"
            ]
        },
        "body": null,
        "statusCode": "CREATED",
        "statusCodeValue": 201
    },
    {
        "headers": {
            "Location": [
                "http://localhost:8080/getorder/2"
            ]
        },
        "body": null,
        "statusCode": "CREATED",
        "statusCodeValue": 201
    },
    {
        "headers": {
            "Location": [
                "http://localhost:8080/getorder/5"
            ]
        },
        "body": null,
        "statusCode": "CREATED",
        "statusCodeValue": 201
    },
    {
        "headers": {
            "Location": [
                "http://localhost:8080/getorder/3"
            ]
        },
        "body": null,
        "statusCode": "CREATED",
        "statusCodeValue": 201
    },
    {
        "headers": {
            "Location": [
                "http://localhost:8080/getorder/4"
            ]
        },
        "body": null,
        "statusCode": "CREATED",
        "statusCodeValue": 201
    }
]
-----------------------------------------------------------------------

To get order summary

URI : http://localhost:8080/getordersummary

Request type : GET

Response:

[
    {
        "orderQuantity": 5.5,
        "pricePerKg": 306
    },
    {
        "orderQuantity": 1.5,
        "pricePerKg": 307
    },
    {
        "orderQuantity": 1.2,
        "pricePerKg": 310
    },
    {
        "orderQuantity": 4,
        "pricePerKg": 306
    },
    {
        "orderQuantity": 4,
        "pricePerKg": 307
    }
]
-------------------------------------------------------------------------------------------------------

To get single order detail

URI : http://localhost:8080/getorder/1

Request type : GET

Response:

{
    "id": 1,
    "userid": "user1",
    "orderQuantity": 3.5,
    "pricePerKg": 306,
    "orderType": "SELL"
}

--------------------------------------------------------------------------------------------------------

Exception handling while sending id for which no order placed:

URI : http://localhost:8080/getorder/12

Request type : GET

Response :

{
    "status": 404,
    "message": "Order with id 12 does not exists."
}
---------------------------------------------------------------------------------------------------------

There is also implemented Exception handling while sending incorrect id (may be some String):

URI : http://localhost:8080/getorder/test

Request type : GET

Response :

{
    "status": 400,
    "message": "No valid order Id found in input"
}
----------------------------------------------------------------------------------------------------------

To cancel all orders:

URI : http://localhost:8080/cancelallorders

Request type : DELETE
----------------------------------------------------------------------------------------------------------

To cancel any one specific order:

URI : http://localhost:8080/cancelorder/1

Request type : DELETE
Response:
{
    "id": 1,
    "userid": "user1",
    "orderQuantity": 3.5,
    "pricePerKg": 306,
    "orderType": "SELL"
}