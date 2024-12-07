package shell.commands.handlers;

import shell.commands.BuiltInCommandHandler;

public class EchoBuiltInCommandHandler implements BuiltInCommandHandler {

    @Override
    public void handle(String arguments) {
        System.out.println(arguments);
    }

}
