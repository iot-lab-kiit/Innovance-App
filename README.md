# RoastMyRoadMap Backend

This repository contains the Spring Boot backend developed for the RoastMyRoadMap event organized by the IoT Lab at Innovance 3.0. The application integrates Redis and OpenAI's API to generate personalized roast messages based on user choices, with Redis utilized for load balancing to optimize API usage and reduce costs.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)

## Features

- **Personalized Roast Generation**: Utilizes OpenAI's API to create roast messages tailored to user inputs.
- **Caching and Load Balancing with Redis**: Implements Redis for efficient caching and load balancing, minimizing API costs.
- **RESTful API**: Provides endpoints for user interactions and roast generation.

## Architecture

The application follows a layered architecture:

1. **Controller Layer**: Handles HTTP requests and responses.
2. **Service Layer**: Contains business logic for processing user inputs and generating roasts.
3. **Repository Layer**: Manages data persistence and retrieval, utilizing Redis for caching and load balancing.

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 11 or higher**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Gradle**: [Download Gradle](https://gradle.org/install/)
- **Redis Server**: [Download Redis](https://redis.io/download)

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/iot-lab-kiit/Innovance-App.git

2. **Navigate to the project directory:**

   ```bash
   cd Innovance-App

3. **Build the application using Gradle:**
   ```bash
   ./gradlew build

## Configuration

1. **Redis Configuration:**

- Ensure the Redis server is running on localhost with the default port 6379. If it's running on a different host or port, update the application.properties file accordingly:

   ```properties
   spring.redis.host=localhost
   spring.redis.port=6379


2. **OpenAI API Key:**

- Obtain your OpenAI API key from the OpenAI Dashboard.

- Set the API key in the application.properties file:

   ```properties
   openai.api.key=YOUR_OPENAI_API_KEY

## Usage
1. **Start the Redis Server:**   

- Ensure that the Redis server is up and running.
2. **Run the Spring Boot Application:**

   ```bash
   ./gradlew bootRun

3. **Access the API:**

- The application will be accessible at http://localhost:8080.
- Use tools like Postman or `curl` to interact with the API endpoints.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.
Ensure that your code adheres to the project's coding standards and includes appropriate tests.
