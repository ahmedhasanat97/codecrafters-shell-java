package shell.commands.handlers;

import shell.commands.BuiltInCommand;
import shell.commands.BuiltInCommandHandler;
import shell.utils.PathCommandsUtil;

import java.io.IOException;
import java.util.Objects;

public class TypeHandlerBuiltIn implements BuiltInCommandHandler {

    @Override
    public void handle(String commandName) {
        try {
            BuiltInCommand builtInCommand = BuiltInCommand.of(commandName);
            if (!Objects.isNull(builtInCommand)) {
                System.out.println(builtInCommand.getDescription());
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
