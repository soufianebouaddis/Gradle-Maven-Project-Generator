package org.os.core;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.os.builder.GradleBuilder.generateGradleBuildFile;
import static org.os.builder.MavenBuilder.generateMavenBuildFile;
import static org.os.builder.StructureBuilder.createProjectStructure;

public class Runner {

    public static void generateProject(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("The input file is empty.");
        }

        // First line specifies the build tool
        String buildTool = lines.get(0).trim().toLowerCase();
        if (!buildTool.equals("maven") && !buildTool.equals("gradle")) {
            throw new IllegalArgumentException("Unsupported build tool: " + buildTool);
        }

        // Parse dependencies
        List<String> dependencies = lines.subList(1, lines.size());
        if (dependencies.isEmpty()) {
            throw new IllegalArgumentException("No dependencies specified.");
        }

        // Generate the project structure
        String projectName = "GeneratedProject";
        String userHome = System.getProperty("user.home");
        Path desktopPath = Paths.get(userHome, "Desktop", projectName);
        createProjectStructure(desktopPath);

        // Generate build file
        if (buildTool.equals("maven")) {
            generateMavenBuildFile(desktopPath, dependencies);
        } else {
            generateGradleBuildFile(desktopPath, dependencies);
        }

        createMainClass(desktopPath);

        System.out.println("Project generated successfully at: " + desktopPath.toAbsolutePath());
    }




    private static void createMainClass(Path projectRoot) throws IOException {
        Path mainClassDir = projectRoot.resolve("src/main/java/com/example");
        Files.createDirectories(mainClassDir);

        Path mainClassPath = mainClassDir.resolve("Main.java");
        try (BufferedWriter writer = Files.newBufferedWriter(mainClassPath)) {
            writer.write("package com.example;\n\n");
            writer.write("public class Main {\n");
            writer.write("    public static void main(String[] args) {\n");
            writer.write("        System.out.println(\"Hello, World!\");\n");
            writer.write("    }\n");
            writer.write("}\n");
        }
    }
}
