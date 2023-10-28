## Deploying Spring Boot Applications with Azure Web Apps and MySQL

This guide will walk you through the process of deploying a Spring Boot application to Azure Web Apps and connecting it to a MySQL database.

**Table of Contents**

1. **Deploying Spring Boot Applications to Azure Web Apps**
  * Azure Web App Gradle Plugin
2. **Installing the Azure CLI**
3. **Deploying Spring Boot Applications to Azure Web Apps**
  * Reviewing Log Streams and Tailing Web App Logs Using the Azure CLI
4. **Code Review of 03 Spring Boot MySQL Web App**
5. **Running MySQL as a Docker Container on Local**
6. **Connecting  Spring Boot MySQL Web App to MySQL on Local**
7. **Creating a MySQL Database in Azure**
8. **Deploying  Spring Boot MySQL Web App to Azure Web Apps**
9. **Connecting  Spring Boot App to Azure MySQL Database - Environment Variables**
10. **Using Azure Cloud Shell to Create a Database**
11. **Connecting to Azure MySQL for Local Machine**
12. **Creating an Azure MySQL Database Using the Azure CLI**
13. **Multi-container using Docker Compose in Azure Web App for Containers**

**Additional Resources**

* [Azure Web App Gradle Plugin documentation](https://github.com/microsoft/azure-gradle-plugins/blob/master/azure-webapp-gradle-plugin/README.md)
* [Azure CLI documentation](https://docs.microsoft.com/en-us/cli/azure/)
* [Azure Web App for Containers documentation](https://docs.microsoft.com/en-us/azure/app-service/containers/)
* [Docker Compose documentation](https://docs.docker.com/compose/)

## Spring Boot - Unit Testing - Mocking with Mockito

**Service test:**

* BlogServiceMockBeanTest class (MockBean: `@MockBean` instead of `@Mock` AND `@Autowired` instead of `@InjectMocks`)
* BlogServiceTest class (Create Mock for BlogRepository And Inject Mock into BlogService)
* BlogServiceDBTest class (Using `@Sql` and JdbcTemplate)

**MVC Controller test:**

* BlogControllerTests class includes tests for all of the following HTTP requests:
  * GET /api/v1/blogs
  * GET /api/v1/blog/1
  * POST /api/v1/create-blog
  * PUT /api/v1/update-blog/1
  * DELETE /api/v1/blog/1
  * Using JsonPath library for querying and navigating JSON documents



