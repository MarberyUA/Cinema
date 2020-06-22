# Cinema

#### What is the goal of this project?
1. Learn such technologies: Spring Core, Spring MVC, Spring Security
1. Learn and train how to work with technologies above
---

#### What can project provide?
* Provide good vision of project structure
* Nice technologies usement
* Good code quality
* An ability to start it up & configure for yourself
---

#### What technologies were used?
* TomCat
* Hibernate
* Log4j
* Maven
* MySQL
* Spring Core
* Spring MVC
* Spring Security
---

#### What principles were user?
* REST
---


### Project functionality:
* Authentication
* Movies management
* Cinema halls management
* Movie sessions management
* Orders management
* Authorization:

*Users with USER role will be able to:*

    - register
    - view all movies
    - add to and remove movie sessions from their shopping cart
    - view shopping cart and complete order
    - view all their orders

*Users with ADMIN role will be able to*

    - add movies to cinema or delete it
    - add cinema halls to cinema
    - do everything that can do simple user
    
# How to launch it?
* Add this project to your IDE as Maven project.
* Add new Tomcat Server configuration and select war-exploded artifact to deploy. Set application context parameter to "/".
* Change path to your log file in src/main/resources/log4j2.properties on line 9. You may also want to change the 'filePattern' parameter on line 12.
* Create database with name "cinema" and set up connection details in src\main\resources\db.properties file 
* Then you just need to start this project up and go to \inject url to create an admin user with such details: administrator@gmail.com:12345
* The last step to check out project functionality is log in as administrator@gmail.com:12345.
---

## What should I know to work with this project? 
* Remember that all request will be in JSON format, so you will need a tool to work with RESTful API. I suggest to use Postman
---

## How to work with Postman & cinema project?

To send your first API request, open Postman. Make sure Build is selected at the bottom right. Click the + plus button to open a new tab.

Enter localhost:8080/users/byemail in the URL field.

In Headers set such keys & values:
*  key - Content-Type, value - application/json
* key - Authorization, value - Basic YWRtaW5pc3RyYXRvckBnbWFpbC5jb206MTIzNDU=

Click Send. You will see the JSON data response from the server in the lower pane.

As a result you should understand that you can get data and send it, so to send data on such urls: \movies, \cinemahalls, you should log in as an admin other POST requests you just should be authenticated, except url: \register
and a few more urls.

Here are the urls access:

Be authenticated at least as user: 

    GET : "/users/byemail"
    GET : "/orders"
    GET : "/shoppingcarts"
    
Be authenticated as admin:

    POST : "/movies"
    POST : "/cinemahalls"
    POST : "/moviesessions"
    
Not be authenticated:
                
    GET : "/movies"
    POST : "/register"
    GET : "/cinemahalls"
    GET : "/moviesessions"
    GET : "/inject"                
                

So now,I describe you how to send data.

Firstly, you should change type of request from GET to POST

Check out Headers if you set up - Content-Type : application/json in Headers

Check out Headers if you set up (if you must be authenticated) - Authentication : Basic username:password in Base64 encode format

Also, choose type raw in Body of request and here are fields which you should be declared in JSON format type for each request:

    POST : "/movies". JSON data : {"title" : "movie-title", "description" : "movie-description"}
    POST : "/cinemahalls". JSON data : {"capacity" : integer-capacity, "cinemahall-description" : "description"}
    POST : "/moviesessions". JSON data : {"movieId" : movie-id, "cinemaHallId" : cinemahall-id, "showDate" : "year-month-day", "showTime" : "hour:minutes"}
    POST : "/register". JSON data : {"email" : "user-email", "name" : "user-name", "password" : "user-password", "repeatPassword" : "password-repeat"}
    GET : "/shoppingcarts". JSON data : {"movieSessionId" : "movieSession-id"}
