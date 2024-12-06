package commands.handlers;

import commands.Command;
import commands.CommandHandler;

import javax.swing.text.html.Option;
import java.util.Optional;

public class TypeHandler implements CommandHandler {

    @Override
    public void handle(String args) {
        System.out.println(Optional.ofNullable(Command.of(args)).map(Command::getDescription).orElse(args + ": not found"));
    }

}
