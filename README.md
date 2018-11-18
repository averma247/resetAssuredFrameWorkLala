# resetAssuredFrameWorkLala
Rest Assured Framework for automating simple REST API flows.

# Item to Install: 
  1. Eclipse
  2. GitHub Desktop and GIT HUB account.
  3. Docker
  4. TestNG plugin in Eclipse.
  
# How to run the above API on your local machine
1. You will need to have docker installed (reference: https://docs.docker.com/install/)
1. Assuming you are under MacOS, type the following commands (It will be very similar on Linux/Unix OS(es)):
$ docker network create lalamove-sample-api || true
$ docker rm -f lalamove-sample-api-db
$ docker run -d --net=lalamove-sample-api --name lalamove-sample-api-db lalamove/lalamove-sample-api-db:1.0
$ docker rm -f lalamove-sample-api
$ docker run -d --net=lalamove-sample-api --name lalamove-sample-api -p 51544:8000 lalamove/lalamove-sample-api:1.0
$ curl -X GET -H "Content-Type: application/json; charset=utf-8" http://localhost:51544/ping # you are successful if you get {"msg":"pong"}
{"msg":"pong"}

# Below are the steps to Setup and Execute the Frame Work.
  1. Create a new folder in your local drive.
  2. Go to that folder in your terminal. 
  3. Execute below git command(Assuming you have already installed GIT).
        git clone https://github.com/averma247/resetAssuredFrameWorkLala.git
  4. Once maven project is cloned in your local open Eclipse and open the Maven project using "Open Projects from File              System."
  5. Once the project is imported, it will download all the required dependecies related to RESTful API, JSON and TestNG.
  6. Now go to Eclipse Market Place and install TestNG plugin.
  7. Once the plugin is installed. Restart the Eclispe.
  8. Now open RunTest.java and select Run As-> TestNG Test.
  9. All the Test Cases will execute. 
  
