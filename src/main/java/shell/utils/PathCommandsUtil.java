package shell.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class PathCommandsUtil {

    public static String findCommandPath(String commandName) throws IOException {
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
