FROM ubuntu:22.04

# Install dependencies
RUN apt-get update && apt-get install -y maven wget unzip

# Download and unzip the project source
RUN wget https://github.com/Merray/Splug/archive/refs/heads/master.zip -O splug-master.zip
RUN unzip splug-master.zip

# Compile the project
WORKDIR /Splug-master
RUN mvn clean install

# Run the Splug application
WORKDIR /Splug-master/target
CMD ["java", "-jar", "./splug-2.0.0-jar-with-dependencies.jar"]

