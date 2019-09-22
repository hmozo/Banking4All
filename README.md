# Banking4All
Backend for a simple "banking" application

* Client should be able to sign up with email & password
* Client should be able to deposit money
* Client should be able to withdraw money
* Client should be able to see the account balance and statement

Database structure, backend and REST API.

Technology stack
* Java 8
* Spring Boot
* in-memory database H2
* Maven as build system
* Host code on GitHub public repo

Others:
- Testing with JUnit/Mockito
- Use of Java 8 features


Note: Spring security is not used.

*************************************************************************

# RESTFUL endpoints

* (POST) /users                                        #to create a User (a PrimaryAccount is created automatically)
	  
      "firstName":"Sergey",
	    "lastName":"Prokov",
	    "email":"sergey3@test.com",
	    "password":"123456"
    
 * (POST) /users/login                                 #to sign in a User (we get a TOKEN and UserId)
 	
      "email":"sergey3@test.com",
	    "password":"123456"
    
  * (GET) /users/{userId}                              #to get User's info (we must add TOKEN in header)
  
      key:Authorization, TOKEN:Bearer...
  
  * (GET) /accounts/{accountNumber}/users/{userId}     #to get the PrimaryAccount's info of the user (we must add the TOKEN in the header)
  
      key:Authorization, TOKEN:Bearer...
  
  * (POST) /accounts/deposit                           #to deposit an amount in the User's PrimaryAccount ((we must add the TOKEN in the header)
    
    	"userId":"8Kh6k9nBH2WiBaCer99fRPLECV8RkI",
	    "accountNumber":"11223146",
	    "amount":"542.21",
	    "description":"TX 45L"
      
  * (POST) /accounts/withdraw                           #to withdraw an amount in the User's PrimaryAccount ((we must add the TOKEN in the header) (There is a withdrawal's limit of 400)
  
      "userId":"8Kh6k9nBH2WiBaCer99fRPLECV8RkI",
	    "accountNumber":"11223146",
	    "amount":"25.3",
	    "description":"TX 45L"
  
  



