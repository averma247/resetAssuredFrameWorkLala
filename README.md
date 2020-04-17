# RESTful APIs Automation test
## Pre-requisite
1. Java above 1.5.
1. Maven version above 3.0.
1. Before executing the test, change the "existingOrderIDForDriveAway" with Orderid in Assiging state in config.properties file. Location of file is at "..\resetAssuredFrameWorkLala\src\test\resources\config.properties".
1.  Before executing the test, change the "existingOrderID","cancelledOrderID", "ongoingOrderID", "completedOrderID" and "assignedOrderID"  with there respective state in config.properties file. Location of file is at "..\resetAssuredFrameWorkLala\src\test\resources\config.properties"

## How to install & Run in eclipse 
1. Please extract the project at your respective path.
1. Open the project as Maven project in eclipse.
1. Build the Project.
1. Now run it as Testng or by right clicking on testng.xml.
1. All the automated test cases will be executed.

## How to install & Run using command prompt
1. Please extract the project at your desired path.
1. Open the command prompt and go to the project path.
1. Run the command "mvn clean install test"
1. All the automated test cases will be executed.

Note: Test cases are available in TestCases.xls for your reference.
