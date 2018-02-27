FROM jamesdbloom/docker-java8-maven

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Prepare by downloading dependencies
ADD pom.xml /app/pom.xml
RUN ["mvn", "dependency:resolve"]
# RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /app/src
RUN ["mvn", "package"]

EXPOSE 1111
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/app/target/testreporter-1.0.jar", "server", "config.yml"]

