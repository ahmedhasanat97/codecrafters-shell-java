package shell.commands;

import shell.commands.handlers.CdBuiltInCommandHandler;
import shell.commands.handlers.EchoBuiltInCommandHandler;
import shell.commands.handlers.ExitBuiltInCommandHandler;
import shell.commands.handlers.PwdBuiltInCommandHandler;
import shell.commands.handlers.TypeBuiltInCommandHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum BuiltInCommand {

    EXIT("exit", "exit is a shell builtin", (args -> new ExitBuiltInCommandHandler().handle(args))),
    ECHO("echo", "echo is a shell builtin", (args -> new EchoBuiltInCommandHandler().handle(args))),
    TYPE("type", "type is a shell builtin", (args -> new TypeBuiltInCommandHandler().handle(args))),
    PWD("pwd", "pwd is a shell builtin", (args -> new PwdBuiltInCommandHandler().handle(args))),
    CD("cd", "cd is a shell builtin", (args -> new CdBuiltInCommandHandler().handle(args)));

    private final String name;
    private final String description;
    private final BuiltInCommandHandler handler;

    private final static Map<String, BuiltInCommand> map = new HashMap<>();

    static {
        Arrays.stream(BuiltInCommand.values()).forEach(builtInCommand -> {
            map.put(builtInCommand.getName(), builtInCommand);
        });
    }

    BuiltInCommand(String name, String description, BuiltInCommandHandler handler) {
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

    public BuiltInCommandHandler getHandler() {
        return handler;
    }

    public static BuiltInCommand of(String name) {
        return map.get(name);
    }

}
