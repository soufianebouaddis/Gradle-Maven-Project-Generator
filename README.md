# Gradle-Maven Project Generator

[![Java](https://img.shields.io/badge/Java-19-blue.svg)](https://www.oracle.com/java/)  
[![Build Tool](https://img.shields.io/badge/Build%20Tools-Maven%20%7C%20Gradle-brightgreen)](https://maven.apache.org/)

A Java-based library that generates a complete project structure (Maven or Gradle) based on a text file specifying dependencies.  
This tool simplifies the process of setting up Java projects by automating dependency integration and project scaffolding.

---

## Features

- **Supports Both Maven and Gradle**: Choose your preferred build tool by specifying it in the input file.
- **Dependency Integration**: Automatically adds the required dependencies to the build file (`pom.xml` for Maven or `build.gradle` for Gradle).
- **Customizable Project Structure**: Generates a standardized project layout:
  - `src/main/java`
  - `src/main/resources`
  - `src/test/java`
  - `src/test/resources`
- **Default Main Class**: Includes a simple `Main.java` file for quick project startup.

---

## How It Works

1. Create a text file with the following format:
   - The **first line** specifies the build tool (`maven` or `gradle`).
   - The **subsequent lines** list dependencies in the format:
     ```
     maven => for maven project
     org.springframework:spring-core:5.3.15 => dependency
     org.apache.commons:commons-lang3:3.12.0 => dependency
     
     gradle => for gradle project
     org.springframework:spring-core:5.3.15 => dependency
     org.apache.commons:commons-lang3:3.12.0 => dependency
     ```  

2. Run the generator with the text file as an argument.

3. The application creates the project on your **Desktop** in a folder named `GeneratedProject`.  
   The folder includes the specified dependencies and project structure.

---

## Example Text File

**`dependencies.txt`**
**Explanation**:
- The **first line** specifies the build tool: `maven` (or `gradle` if you prefer Gradle).
- Each subsequent line is a dependency in the format: `groupId:artifactId:version`.

---

## Installation

### Clone the Repository
```bash
git clone https://github.com/soufianebouaddis/Gradle-Maven-Project-Generator.git
cd Gradle-Maven-Project-Generator
java -jar target/generator.jar <path-to-dependencies-file>

GeneratedProject/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/Main.java
│   │   └── resources/
│   ├── test/
│       ├── java/
│       └── resources/
