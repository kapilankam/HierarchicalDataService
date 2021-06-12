# Start with a base image containing Java runtime
FROM java:8

# Add Maintainer Info
LABEL maintainer="kapil.ankam@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8083 available to the world outside this container
EXPOSE 8083

#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8083,server=y,suspend=n

# The application's jar file
ARG JAR_FILE=target/HierarchicalDataService-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /root/HierarchicalDataService-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/root/HierarchicalDataService-0.0.1-SNAPSHOT.jar"]
