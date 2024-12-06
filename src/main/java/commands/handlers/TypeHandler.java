package commands.handlers;

import commands.Command;
import commands.CommandHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TypeHandler implements CommandHandler {

    @Override
    public void handle(String commandName) {
        try {
            Command command = Command.of(commandName);
            if (!Objects.isNull(command)) {
                System.out.println(command.getDescription());
                return;
            }

            String commandPath = findCommandPath(commandName);

            if (!Objects.isNull(commandPath)) {
                System.out.println(commandName + " is " + commandPath);
                return;
            }

            System.out.println(commandName + ": not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String findCommandPath(String commandName) throws IOException {
        String pathEnv = System.getenv("PATH");
        if (Objects.isNull(pathEnv)) {
            return null;
        }

        String[] directoriesPath = pathEnv.split(":");
        for (String directoryPath : directoriesPath) {
            Path path = Path.of(directoryPath, commandName);
            if (Files.isRegularFile(path)) {
                return path.toString();
            }
        }

        return null;
    }

}
