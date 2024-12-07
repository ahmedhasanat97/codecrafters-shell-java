package shell.commands.handlers;

import shell.commands.BuiltInCommandHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;

public class CdBuiltInCommandHandler implements BuiltInCommandHandler {

    @Override
    public void handle(String arguments) {
        Path pth = Path.of(getPath(arguments));
        if (!Files.exists(pth) || !Files.isDirectory(pth)) {
            System.out.println("cd: " + arguments + ": No such file or directory");
            return;
        }
        System.setProperty("user.dir", pth.toString());
    }

    private String getPath(String arguments) {
        String[] argumentStrings = arguments.split("/");
        if (argumentStrings.length == 0) {
            return "/";
        }

        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        if (argumentStrings[0].equals(".") || argumentStrings[0].equals("..")) {
            String currentDir = System.getProperty("user.dir");
            String[] currentDirStrings = currentDir.split("/");
            for (String dir: currentDirStrings) {
                arrayDeque.addLast(dir);
            }
        }

        for (String pathArgument: argumentStrings) {
            switch (pathArgument) {
                case ".":
                    // nothing to do
                    break;
                case "..":
                    if (!arrayDeque.isEmpty()) {
                        arrayDeque.removeLast();
                    }
                    break;
                default:
                    arrayDeque.addLast(pathArgument);
            }
        }

        if (arrayDeque.isEmpty()) {
            return "/";
        }

        StringBuilder builder = new StringBuilder();
        while (!arrayDeque.isEmpty()) {
            builder.append("/").append(arrayDeque.removeFirst());
        }

        return builder.toString();
    }

}
