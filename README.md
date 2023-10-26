# Deploying Spring Boot Applications with Azure Web Apps and MySQL

This guide will walk you through the process of deploying a Spring Boot application to Azure Web Apps and connecting it to a MySQL database using various steps and tools.

## Table of Contents

- [Step 01 - Deploy Spring Boot Application to Azure Web Apps](#step-01-deploy-spring-boot-application-to-azure-web-apps)
    - [Azure Web App Gradle Plugin](#azure-web-app-gradle-plugin)
- [Step 02 - Install Azure CLI](#step-02-install-azure-cli)
- [Step 03 - Deploy Spring Boot Application to Azure Web Apps](#step-03-deploy-spring-boot-application-to-azure-web-apps)
    - [Review Log Streams and Tail Web App Logs using Azure CLI](#review-log-streams-and-tail-web-app-logs-using-azure-cli)
- [Step 04 - Code Review of 03 Spring Boot MySQL Web App](#step-04-code-review-of-03-spring-boot-mysql-web-app)
- [Step 05 - Running MySQL as Docker Container on Local](#step-05-running-mysql-as-docker-container-on-local)
- [Step 06 - Connect 03 Spring Boot MySQL Web App to MySQL on Local](#step-06-connect-03-spring-boot-mysql-web-app-to-mysql-on-local)
- [Step 07 - Creating MySQL Database in Azure](#step-07-creating-mysql-database-in-azure)
- [Step 08 - Deploy 03 Spring Boot MySQL Web App to Azure Web Apps](#step-08-deploy-03-spring-boot-mysql-web-app-to-azure-web-apps)
- [Step 09 - Connect 03 Spring Boot App to Azure MySQL Database - Environment Variables](#step-09-connect-03-spring-boot-app-to-azure-mysql-database-environment-variables)
- [Step 10 - Using Azure Cloud Shell to Create Database](#step-10-using-azure-cloud-shell-to-create-database)
- [Step 11 - Connecting to Azure MySQL for Local Machine](#step-11-connecting-to-azure-mysql-for-local-machine)
- [Step 12 - Create Azure MySQL Database using Azure CLI](#step-12-create-azure-mysql-database-using-azure-cli)

## Step 01 - Deploy Spring Boot Application to Azure Web Apps

In this step, you will learn how to deploy a Spring Boot application to Azure Web Apps using the Azure Web App Gradle Plugin.

### Azure Web App Gradle Plugin

Instructions on how to use the Azure Web App Gradle Plugin to deploy your Spring Boot application.

## Step 02 - Install Azure CLI

Learn how to install the Azure Command-Line Interface (CLI), which is essential for Azure-related tasks.

## Step 03 - Deploy Spring Boot Application to Azure Web Apps

Continue the deployment process of your Spring Boot application to Azure Web Apps.

### Review Log Streams and Tail Web App Logs using Azure CLI

Learn how to review log streams and tail web app logs using the Azure CLI to troubleshoot issues.

## Step 04 - Code Review of 03 Spring Boot MySQL Web App

Review the code of your Spring Boot application that interacts with a MySQL database.

## Step 05 - Running MySQL as Docker Container on Local

Instructions on running a MySQL database as a Docker container on your local machine.

## Step 06 - Connect 03 Spring Boot MySQL Web App to MySQL on Local

Learn how to connect your Spring Boot application to the locally running MySQL database.

## Step 07 - Creating MySQL Database in Azure

Detailed steps to create a MySQL database in Azure for your application.

## Step 08 - Deploy 03 Spring Boot MySQL Web App to Azure Web Apps

Deploy your Spring Boot application, which is now configured to use the Azure MySQL database, to Azure Web Apps.

## Step 09 - Connect 03 Spring Boot App to Azure MySQL Database - Environment Variables

Configure environment variables for your Spring Boot application to connect to the Azure MySQL database.

## Step 10 - Using Azure Cloud Shell to Create Database

Utilize Azure Cloud Shell to create and manage databases in Azure.

## Step 11 - Connecting to Azure MySQL for Local Machine

Instructions on how to connect to the Azure MySQL database from your local machine for development and debugging.

## Step 12 - Create Azure MySQL Database using Azure CLI

Learn how to create an Azure MySQL database using the Azure Command-Line Interface (CLI).

## Step 13: - Multi-container using Docker Compose in Azure Web App for Containers

# Unit Testing - Mocking with Mockito

## Spring Boot Unit Testing - Mocking with Mockito:
   1. Service test: 
      1. BlogServiceMockBeanTest class (MockBean: @MockBean instead of @Mock AND @Autowired instead of @InjectMocks)
      2. BlogServiceTest class (Create Mock for BlogRepository And Inject Mock into BlogService)
      3. BlogServiceDBTest class (Using @Sql)


