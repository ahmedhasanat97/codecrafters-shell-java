package shell;

import shell.commands.Command;
import shell.utils.PathCommandsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class Shell implements Runnable {

    @Override
    public void run() {

        try {
            while (true) {
                System.out.print("$ ");

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                String commandName = input;
                String arguments = "";
                int indexOfFirstSpace = input.indexOf(" ");
                if (indexOfFirstSpace != -1) {
                    commandName = input.substring(0, indexOfFirstSpace);
                    arguments = input.substring(indexOfFirstSpace + 1);
                }

                // handle it using predefined commands
                Command commandEnum = Command.of(commandName);
                if (!Objects.isNull(commandEnum)) {
                    commandEnum.getHandler().handle(arguments);
                    continue;
                }

                // handle it using path commands
                String commandPath = PathCommandsUtil.findCommandPath(commandName);
                if (!Objects.isNull(commandPath)) {
                    String response = runFile(commandName, arguments);
                    System.out.println(response);
                    continue;
                }

                System.out.println(commandName + ": not found");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private String runFile(String commandPath, String arguments) throws IOException, InterruptedException {
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
