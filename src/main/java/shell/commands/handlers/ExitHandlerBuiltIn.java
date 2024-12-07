package shell.commands.handlers;

import shell.commands.BuiltInCommandHandler;

public class ExitHandlerBuiltIn implements BuiltInCommandHandler {

    @Override
    public void handle(String statusCode) {
        if ("0".equals(statusCode)) {
            System.exit(0);
        }
    }

}
