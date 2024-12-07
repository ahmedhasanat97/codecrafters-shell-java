package shell.commands.handlers;

import shell.commands.BuiltInCommandHandler;

import java.nio.file.Files;
import java.nio.file.Path;

public class CdBuiltInCommandHandler implements BuiltInCommandHandler {

    @Override
    public void handle(String arguments) {
        Path absolutePath = Path.of(arguments);
        if (!Files.exists(absolutePath) || !Files.isDirectory(absolutePath)) {
            System.out.println("cd: " + arguments + ": No such file or directory");
            return;
        }
        System.setProperty("user.dir", absolutePath.toString());
    }

}
