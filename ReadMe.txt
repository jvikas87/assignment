#Make database assignment in mysql (Properties are mentioned in mysql.properties).

Document Related to API:

1. I assumed that this API is called from Admin Only and Admin passing valid infomation only i.e 
username,account number are valid.so I skip on validation part.
API URL : http://localhost:8080/assignment/bankaccount/create
Request Body:{"accountNumber":"xxx" ,"username":"xxx","password":"xxx","currency":"xxx","accountBalance":xxx}
so this infomation is saved into DB and account primary key is generated.And I am using this id to link with other table.
Type Of Request : POST

2.Check balance API:http://localhost:8080/assignment/bankaccount/create where account number passed in Request Parameter.
and we get BankAccount object contains all the information related to that account.
Type Of Request : GET

3.API for add beneficiary:http://localhost:8080/assignment/add/beneficiary and Request Body is passed
{"beneficiaryAccountId":xxxx} and I assumed that beneficiary is not added already.I added validation that
beneficiaryAccountId should be valid i.e account with beneficiaryAccountId should exist
Type Of Request : POST

4.API for remove beneficiary:http://localhost:8080/assignment/remove/beneficiary and requestBody Contains
Id (i.e primary key generated after we have add beneficiary).I added validation to check that beneficiary 
should be there in account holder beneficiary's list.
{"id":xxx}
Type Of Request : POST

5.API for transaction:http://localhost:8080/assignment/transaction and in request body we have passed
beneficiary id (means primary key for Beneficiary) and amount that we want to transfer to.If Currency
of account holder is different that currency of beneficiary than we evalute equivalent money in account
holder currency and subtract that amount from account and add the amonut which is passed in Request
Body to beneficiary account's.
Apart from this two transaction row is generated.one for debit and one for credit.In both these
row from account is the column which is related to DEBIT or CREDIT.
{"beneficiaryId":"xxx","amount":xxxx}
Type Of Request : POST

6.API for transaction detail.http://localhost:8080/assignment/transaction/detail?startDate=2017-06-28&endDate=2017-06-28
return the list of transaction.
Type Of Request : GET

7.API for login : http://localhost:8080/assignment/login.Username and Password is passed in Request Body.
{"username":"xxx","password":"xxx"}
Type Of Request : POST
