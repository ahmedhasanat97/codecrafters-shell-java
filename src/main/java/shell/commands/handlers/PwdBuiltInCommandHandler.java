package shell.commands.handlers;

import shell.commands.BuiltInCommandHandler;

public class PwdBuiltInCommandHandler implements BuiltInCommandHandler {

    @Override
    public void handle(String arguments) {
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
    }

}
