package shell.commands.handlers;

import shell.commands.CommandHandler;

public class ExitHandler implements CommandHandler {

    @Override
    public void handle(String args) {
        if ("0".equals(args)) {
            System.exit(0);
        }
    }

}
