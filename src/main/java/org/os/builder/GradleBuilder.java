package org.os.builder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GradleBuilder {
    public static void generateGradleBuildFile(Path projectRoot, List<String> dependencies) throws IOException {
        Path buildFile = projectRoot.resolve("build.gradle");
        System.out.println("build.gradle generated successfully");
        try (BufferedWriter writer = Files.newBufferedWriter(buildFile)) {
            System.out.println("Start injecting the dependencies");
            writer.write("plugins {\n");
            writer.write("    id 'java'\n");
            writer.write("}\n\n");

            writer.write("// Project Metadata\n");
            writer.write("group = 'com.example'\n");
            writer.write("version = '1.0-SNAPSHOT'\n\n");

            writer.write("repositories {\n");
            writer.write("    mavenCentral()\n");
            writer.write("}\n\n");

            writer.write("dependencies {\n");

            for (String dependency : dependencies) {
                String[] parts = dependency.split(":");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid Gradle dependency format: " + dependency);
                }
                writer.write("    implementation '" + parts[0] + ":" + parts[1] + ":" + parts[2] + "'\n");
            }

            writer.write("}\n\n");

            writer.write("// Add Java version compatibility\n");
            writer.write("java {\n");
            writer.write("    toolchain {\n");
            writer.write("        languageVersion = JavaLanguageVersion.of(19)\n");
            writer.write("    }\n");
            writer.write("}\n");
        }
    }

}
