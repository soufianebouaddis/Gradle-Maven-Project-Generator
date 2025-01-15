package org.os.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StructureBuilder {
    public static void createProjectStructure(Path projectRoot) throws IOException {
        Files.createDirectories(projectRoot.resolve("src/main/java/com/example"));
        System.out.println("src/main/java/com/example generated");
        Files.createDirectories(projectRoot.resolve("src/main/resources"));
        System.out.println("src/main/resources generated");
        Files.createDirectories(projectRoot.resolve("src/test/java"));
        System.out.println("src/test/java generated");
        Files.createDirectories(projectRoot.resolve("src/test/resources"));
        System.out.println("src/test/resources generated");
    }
}
