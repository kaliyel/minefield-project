# syntax=docker/dockerfile:1

# this is the base image I'll use to build and execute java code
#riprova a mettere jdk11
FROM openjdk:8

# I run these two commands to install the dependencies for the build and execution of the app
RUN apt-get update
RUN apt-get install -y maven libxrender1 libxtst6 libxi6

# I copy all the code of the project inside the container in a directory called /usr/minefield
COPY . /usr/minefield

# basically i do 'cd /usr/minefield'
WORKDIR /usr/minefield

# i build the project to generate the jar file
RUN mvn clean package

# finally i run the produced jar file
CMD ["java", "-jar", "./gameui/target/MinefieldGame.jar"]