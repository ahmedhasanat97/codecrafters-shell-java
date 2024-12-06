package shell.commands.handlers;

import shell.commands.Command;
import shell.commands.CommandHandler;
import shell.utils.PathCommandsUtil;

import java.io.IOException;
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

            String commandPath = PathCommandsUtil.findCommandPath(commandName);

            if (!Objects.isNull(commandPath)) {
                System.out.println(commandName + " is " + commandPath);
                return;
            }

            System.out.println(commandName + ": not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
