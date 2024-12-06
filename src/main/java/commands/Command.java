package commands;

import commands.handlers.EchoHandler;
import commands.handlers.ExitHandler;
import commands.handlers.TypeHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Command {

    EXIT("exit", "exit is a shell builtin", (args -> new ExitHandler().handle(args))),
    ECHO("echo", "echo is a shell builtin", (args -> new EchoHandler().handle(args))),
    TYPE("type", "type is a shell builtin", (args -> new TypeHandler().handle(args)));

    private final String name;
    private final String description;
    private final CommandHandler handler;

    private final static Map<String, Command> map = new HashMap<>();

    static {
        Arrays.stream(Command.values()).forEach(command -> {
            map.put(command.getName(), command);
        });
    }

    Command(String name, String description, CommandHandler handler) {
        this.name = name;
        this.description = description;
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public static Command of(String name) {
        return map.get(name);
    }

}
