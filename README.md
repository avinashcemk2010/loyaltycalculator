# loyaltycalculator

Requirement:

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Steps to run the code:

Download code from Git Repo: https://github.com/avinashcemk2010/loyaltycalculator.git
Run maven clean
Run maven install
Run the project
Use Rest client and verify the result by calling url: http://localhost:8080/api/v1/loyalty/points/{id}

DB: H2
URL: http://localhost:8080/h2-console/
Password: password (mentioned in application.properties)

Framework/Language/DB used:
Java 8, spring boot, junit, H2, Rest

Technical details:
when the project comes up spring boot uses data.sql to insert the data into H2 db
Two tables gets created customer and transactions
Customer table holds customer details whereas transaction holds the transaction done by those customers
	

