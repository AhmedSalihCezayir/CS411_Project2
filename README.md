# CS411_Project2
Project consists of three main parts: database, frontend and backend. To start the project all of these parts need to be started.

First of all, PostgreSQL database need to be created. After it is created, enter its url, username and password information to `application.properties` file. This file is located under src/main/resources. After database is created and connected, frontend and backend side can be started.

To start the backend side, enter to the background folder. After entering, run: `./gradlew bootRun`. This command will run the project and start the backend side.

To start the frontend side, enter to the frontend folder. After entering, if this is the first time starting the project run: `npm install`. If this is not the first time running this code, do not run that command. After this step run: `npm start`. This command will start the application frontend at `localhost:3000`.

After the above procedures are done, program can be used at `localhost:3000`.

NOTES: 
To be able to use the application following applications and versions are needed:
* Java 8+
* Nodejs 14+
