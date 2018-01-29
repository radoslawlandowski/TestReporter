# Use an official Python runtime as a parent image
FROM alinous/docker-java-postgresql

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

RUN yum install wget -y
RUN yum install sudo -y

RUN wget http://mirror.cc.columbia.edu/pub/software/apache/maven/maven-3/3.5.2/binaries/apache-maven-3.5.2-bin.tar.gz
RUN tar -zxvf apache-maven-3.5.2-bin.tar.gz
RUN sudo mv ~/apache-maven-3.5.2 /opt
RUN sudo chown -R root:root /opt/apache-maven-3.5.2
RUN sudo ln -s /opt/apache-maven-3.5.0 /opt/apache-maven
RUN echo 'export PATH=$PATH:/opt/apache-maven/bin' | sudo tee -a /etc/profile
RUN source /etc/profile


# Prepare by downloading dependencies
ADD pom.xml /app/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /app/src
RUN ["mvn", "package"]

EXPOSE 1111
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/app/target/testreporter-1.0.jar", "server", "config.yml"]

