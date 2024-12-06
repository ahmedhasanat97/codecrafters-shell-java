import commands.Command;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.print("$ ");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String command = input;
            String arguments = "";
            int indexOfFirstSpace = input.indexOf(" ");
            if (indexOfFirstSpace != -1) {
                command = input.substring(0, indexOfFirstSpace);
                arguments = input.substring(indexOfFirstSpace + 1);
            }

            Command commandEnum = Command.of(command);
            if (Objects.isNull(commandEnum)) {
                System.out.println(command + ": not found");
                continue;
            }

            commandEnum.getHandler().handle(arguments);

        }

    }
}
