FROM  openjdk:17-jdk-slim
WORKDIR app
# Copy your specific JAR file into the container at /app
COPY  /build/libs/azure-blog-app.jar /app/azure-blog-app.jar

# Run the application when the container starts
ENTRYPOINT ["java", "-jar","/app/azure-blog-app.jar"]

# Make port 5000 available to the world outside this container
EXPOSE 8081