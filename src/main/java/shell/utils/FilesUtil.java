package shell.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FilesUtil {

    public static String runFile(String commandPath, String arguments) throws IOException, InterruptedException {
        // ProcessBuilder to execute the file
        ProcessBuilder processBuilder = new ProcessBuilder(commandPath, arguments);
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        StringBuilder builder = new StringBuilder();

        // Reading the output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }

        // Wait for the process to complete
        process.waitFor();

        return builder.toString();
    }

}
