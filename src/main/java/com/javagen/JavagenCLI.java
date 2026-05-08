package com.javagen;

import com.javagen.cli.CreateCommand;
import com.javagen.utils.Constants;
import picocli.CommandLine;

@CommandLine.Command(
    name = Constants.APP_NAME,
    mixinStandardHelpOptions = true,
    version = Constants.APP_VERSION,
    description = Constants.APP_DESCRIPTION,
    subcommands = {CreateCommand.class}
)
public class JavagenCLI implements Runnable {

    @Override
    public void run() {
        System.out.println(Constants.APP_NAME + " v" + Constants.APP_VERSION);
        System.out.println("Usage: javagen create <type> [options]");
        System.out.println("Types: web, desk, app");
        System.out.println("Run 'javagen --help' for more info.");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new JavagenCLI()).execute(args);
        System.exit(exitCode);
    }
}
