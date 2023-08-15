# Reimbursement application description:
This application serves as a reimbursement calculator. It allows users to input the number of days and distance travelled, then calculates the reimbursement amount based on global rates set in the configuration.

The application is built using plain Java 11 and employs the com.sun.net.httpserver package to create a simple HTTP server, serving HTML content and processing user input. The front-end is based on HTML, CSS and JavaScript.

# To login as administrator you need to input:
Login: admin
Password: admin

# Make sure you have the necessary tools and libraries installed:

JDK 11 (Java Development Kit) for the Java version the project uses. 
(Amazon Corretto 11.0.20).
Maven to manage dependencies in pom.xml file. The only dependency is for JUnit to test the application.

The application runs on localhost:8080, so make sure this port is free. 
You can change the port in this line:
 HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); 
in MainApp.java class. 

If you do not want to change the port run command line and type: “netstat –ano”, look for PID number from list with 8080 port. 
Then type “taskkill /PID 8080 /F” replace PID with number showed on port 8080.

# How to set-up environment on Windows:
If you are using IntelliJ IDE: 
Go to File -> New -> Project from version control -> Paste in URL: field link from GitHub repository : https://github.com/direeX/Java_Reimbursement_Application_Final and press Clone.
Run the application from class MainApp.java.

If you are using Git Bash :
Right click on the field inside empty folder where you want to clone application, 
then select Git Bash Here. 
When terminal opens type or copy paste: 
git clone https://github.com/direeX/Java_Reimbursement_Application_Final
Then open project folder with your IDE.

# Back-end structure:
MainHandler: This handler is responsible for serving static resources, such as CSS files and the main HTML page.

UserHandler: Handles user interactions. It provides an HTML form for user input and processes POST requests to calculate the reimbursement amount based on form data.

AdminHandler class offers functionalities to manage the administrative view of the application. It lets users view and edit reimbursement claims and also allows for the modification of rates used in reimbursement calculations.

SubmitHandler handles the task of processing newly submitted reimbursement claims, saving them, calculating the reimbursement amount, and sending a response back to the client.

AppConfig class acts as a centralized storage and configuration space for the application, keeping track of claims and allowing retrieval and updating of rates.

Claim class is a simple data structure that models a reimbursement claim, allowing for the creation, modification, and retrieval of claim details.

ReceiptRepository class provides a simple mechanism to manage and store receipt data, allowing for data addition and retrieval.

MainApp class sets up and starts an HTTP server, directing incoming HTTP requests to their respective handlers based on the request URL.

# Front-end structure:
admin.html: This is the admin panel page. It displays submitted claims and has a button to return to the home page. The dynamic content will be added to the "claims-data-container" div.

index.html: This is the main page of web application. It provides two buttons - one for users and one for admins to redirect them to respective pages.

login.html: This is the login page for the application. It provides fields for username and password, along with a login button.

user.html: This is the user panel where users can submit their claims for reimbursement. It includes a form to capture trip details.

admin-scripts.js: This contains JavaScript functions for the admin page. Functions include redirecting back to home, submitting edits, fetching and displaying claims data from the server, and submitting rate changes to the server.

scripts.js: This contains JavaScript functions for the main page and user page. Functions include redirecting users or admins to their respective pages, checking login credentials, and submitting reimbursement forms.

styles.css: This is the stylesheet for the entire application. It provides styles for body, buttons, containers, labels, inputs, etc. It has responsive styling for devices with a max-width of 650px.

# Interactions and Flow:
Users can submit claims through /submit, which are then stored in ReceiptRepository and as Claim objects in AppConfig.
An admin can view and edit the submitted claims and modify the rates via /admin.

# Running Tests
To execute the unit tests:
Ensure you have JUnit installed. If not, you can add it to your project using a dependency manager - Maven.
In your IDE navigate to the directory containing the test classes, then run AppTests.java
test -> java -> AppTests

# Bonus:
To integrate calculation rules written in JavaScript with a Java application, we can use Java's built-in JavaScript engine, such as Nashorn (in Java 8) or GraalVM (in newer versions). Load the JavaScript script in Java, and then invoke the relevant functions from that script.






