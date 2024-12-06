package commands.handlers;

import commands.CommandHandler;

public class EchoHandler implements CommandHandler {

    @Override
    public void handle(String args) {
        System.out.println(args);
    }

}
