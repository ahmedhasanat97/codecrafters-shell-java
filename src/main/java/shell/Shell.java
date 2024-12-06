package shell;

import shell.commands.Command;
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

                // handle it using predefined commands
                Command commandEnum = Command.of(commandName);
                if (!Objects.isNull(commandEnum)) {
                    commandEnum.getHandler().handle(arguments);
                    continue;
                }

                // handle it using path commands
                String commandPath = PathCommandsUtil.findCommandPath(commandName);
                if (!Objects.isNull(commandPath)) {
                    Process process = new ProcessBuilder(commandName, arguments).start();
                    continue;
                }

                System.out.println(commandName + ": not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
