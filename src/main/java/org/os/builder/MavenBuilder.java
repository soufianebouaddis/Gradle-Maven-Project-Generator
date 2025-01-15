package org.os.builder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MavenBuilder {

    public static void generateMavenBuildFile(Path projectRoot, List<String> dependencies) throws IOException {
        Path pomFile = projectRoot.resolve("pom.xml");
        System.out.println("pom.xml generated successfully");
        try (BufferedWriter writer = Files.newBufferedWriter(pomFile)) {
            System.out.println("start injecting the dependencies");
            writer.write("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n");
            writer.write("         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            writer.write("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n");
            writer.write("    <modelVersion>4.0.0</modelVersion>\n");
            writer.write("    <groupId>com.example</groupId>\n");
            writer.write("    <artifactId>generated-project</artifactId>\n");
            writer.write("    <version>1.0-SNAPSHOT</version>\n");
            writer.write("    <dependencies>\n");

            for (String dependency : dependencies) {
                String[] parts = dependency.split(":");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid Maven dependency format: " + dependency);
                }
                writer.write("        <dependency>\n");
                writer.write("            <groupId>" + parts[0] + "</groupId>\n");
                writer.write("            <artifactId>" + parts[1] + "</artifactId>\n");
                writer.write("            <version>" + parts[2] + "</version>\n");
                writer.write("        </dependency>\n");
            }

            writer.write("    </dependencies>\n");
            writer.write("</project>\n");
        }
    }
}
