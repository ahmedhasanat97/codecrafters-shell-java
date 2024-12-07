package shell;

import shell.commands.BuiltInCommand;
import shell.utils.FilesUtil;
import shell.utils.PathCommandsUtil;

import java.io.IOException;
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

                // handle it using built in commands
                BuiltInCommand builtInCommandEnum = BuiltInCommand.of(commandName);
                if (!Objects.isNull(builtInCommandEnum)) {
                    builtInCommandEnum.getHandler().handle(arguments);
                    continue;
                }

                // handle it using path commands
                String commandPath = PathCommandsUtil.findCommandPath(commandName);
                if (!Objects.isNull(commandPath)) {
                    String response = FilesUtil.runFile(commandName, arguments);
                    System.out.println(response);
                    continue;
                }

                System.out.println(commandName + ": not found");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
