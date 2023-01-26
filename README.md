# api_homework
    # Retail Rewards Program

## About

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent between $50 and $100 in each transaction.

`(e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).`

Given a record of every transaction during a three-month period, calculate the reward points earned for
each customer per month and total

## Running application in local

Please follow below instructions to build the application and run it in local:
* Navigate to the location where pom.xml is present
* Clean the project: `./mvnw clean`
* Build the project: `./mvnw install`
* Run the application: `./mvnw spring-boot:run`

## Exposed APIs
* `http://localhost:8088/rewards` : Get reward details for all customers
* `http://localhost:8088/rewards/<customerId>` : Get reward details by customer id
* `http://localhost:8088/transactions` : Get all transactions available


    

