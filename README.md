#   Tweet App - Spring Boot Implementation

##   Overview

The   Tweet App is a Single Page Application (SPA) that allows registered users to post new tweets, reply to tweets, and like/unlike tweets. Guest users are not authorized to view any tweets. This document outlines the project's requirements, architecture, and development guidelines.

The   core modules of this application are:

* User registration and login
* Posting tweets
* Viewing users/handles and their respective tweets

This   implementation uses Spring Boot for the backend services instead of .NET.

##   Key Features

* **User Authentication**: Users can register and log in to the application.
    * Users can log out.
    * Registration requires: First Name, Last Name, Email, Login ID, Password, Confirm Password, and Contact Number.
    * All registration fields are mandatory.
    * Login ID and Email must be unique.
    * Password and Confirm Password must match.
    * Validations are in place to ensure data integrity.
* **Tweet Management**:
    * Users can post new tweets, with a 144-character limit.
    * Tweets can optionally include tags (up to 50 characters).
    * Users can view other users' tweets and reply to them.
    * Replies also have a 144-character limit and can include tags.
    * Tweets and replies display the username and timestamp.
* **User Interaction**: Users can view other users and their tweets.

##   Technology Stack

* **Backend**: Spring Boot
* **Frontend**: (As per the document, Angular is used for the frontend) Angular
* **Database**: MySQL
* **Build Tool**: Maven or Gradle
* **Containerization**: Docker
* **Version Control**: Git

##   Application Architecture

The   architecture follows a layered structure:

* **Presentation Layer**: Handles the UI components (built with Angular), managing user interactions and displaying data.
* **Service Layer**: The Spring Boot application provides RESTful APIs to manage user authentication, tweets, and user data.
* **Data Access Layer**: Interacts with the MySQL database using Spring Data JPA or a similar ORM to persist and retrieve data.

\[*The   document includes a diagram showing the architecture, which would be helpful to include here visually.*]

##   REST API Endpoints

Based   on the provided controllers (AuthController.java and TweetController.java), the following table describes the API endpoints:

**Authentication   Controller (AuthController)**

|   HTTP Method   |   Endpoint                     |   Description                |   Notes   |
| :-------------- | :--------------------------- | :------------------------- | :------ |
|   POST          |   /api/v1.0/tweets/register    |   Register a new user        |           |
|   POST          |   /api/v1.0/tweets/login       |   User login                 |           |
|   GET           |   /api/v1.0/tweets/{username}/get  |   Get user by username       |           |
|   GET           |   /api/v1.0/tweets/users/all   |   Get all users              |           |
|   GET           |   /api/v1.0/tweets/user/search/{username} |   Search users by username |           |

**Tweet   Controller (TweetController)**

|   HTTP Method   |   Endpoint                           |   Description                       |   Notes   |
| :-------------- | :--------------------------------- | :-------------------------------- | :------ |
|   GET           |   /api/v1.0/tweets/all               |   Get all tweets (paginated)        |           |
|   GET           |   /api/v1.0/tweets/all/{id}          |   Get tweet by ID                   |           |
|   GET           |   /api/v1.0/tweets/all/{id}/replies  |   Get replies to a tweet (paginated) |           |
|   GET           |   /api/v1.0/tweets/{username}        |   Get all tweets by user (paginated) |           |
|   POST          |   /api/v1.0/tweets/{username}/add    |   Post a new tweet                    |           |
|   PUT           |   /api/v1.0/tweets/{username}/update/{id} |   Update a tweet                      |           |
|   POST          |   /api/v1.0/tweets/{username}/delete/{id} |   Delete a tweet                      |           |
|   PUT           |   /api/v1.0/tweets/{username}/like/{id}   |   Like a tweet                        |           |
|   POST          |   /api/v1.0/tweets/{username}/reply/{id}  |   Reply to a tweet                    |           |

\*`{username}`   and `{id}` are path variables.

##   Database Configuration

The   application uses MySQL to store user data and tweets. Spring Data JPA or a similar ORM (Object-Relational Mapping) library is used to interact with the database. Configuration details (e.g., connection URL, username, password) are typically stored in `application.properties` or `application.yml` files.

##   Project Structure

The   Spring Boot project follows a standard package structure (e.g., `com.tweetapp.*`). Classes are organized by functionality (e.g., controllers, services, repositories, models).

##   Dependency Injection

The   project employs constructor-based dependency injection for most components, and setter-based injection where appropriate.

##   API Documentation

The   REST API endpoints are documented using Swagger or OpenAPI to facilitate development and testing.

##   Development Guidelines

* **Code Quality**: Write efficient, clean, readable, and testable code.
* **Secure Coding**: Apply basic secure coding principles and avoid deprecated functions.
* **Logging and Monitoring**: Implement logging to track application behavior and errors. Containerize the application using Docker. Use `.dockerignore` to exclude unnecessary files from the Docker image.
* **Error Handling**: Generate bug reports and error logs, including suggested resolutions.
* **Agile Methodology**: Use a project management tool to track progress, employ estimation techniques (e.g., story points), and follow Agile principles.

##   Further Development

This   README provides a foundation for the Tweet App. Further development can include:

* Adding unit and integration tests.
* Enhancing the UI/UX.
* Implementing robust error handling and validation.
* Optimizing performance and scalability.