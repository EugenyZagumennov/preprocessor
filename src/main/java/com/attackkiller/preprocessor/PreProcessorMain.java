package com.attackkiller.preprocessor;

import com.attackkiller.preprocessor.core.domain.CommandLineParam;
import com.attackkiller.preprocessor.service.PreProcessorService;
import org.apache.commons.cli.*;

/**
 * Program runner
 *
 * @author evgenii.zagumennov
 */
public class PreProcessorMain {

    private static String cSharpDir;
    private static String symbolsFile;
    private static String outputDir;

    private PreProcessorService preProcessorService = new PreProcessorService();

    public static void main(String[] args) {
        new PreProcessorMain().run(args);
    }

    private void run(String[] args){
        fillOptions(args);
        preProcessorService.run(cSharpDir, symbolsFile, outputDir);
    }

    /**
     * Fill options with command line params
     *
     * @param args
     */
    private void fillOptions(String[] args){
        Options options = new Options();

        for(CommandLineParam param: CommandLineParam.values()) {
            Option input = new Option(param.getOpt(), param.getLongOpt(), param.isHasArg(), param.getDescription());
            input.setRequired(true);
            options.addOption(input);
        }

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        cSharpDir = cmd.getOptionValue(CommandLineParam.CSHARPDIR.getLongOpt());
        symbolsFile = cmd.getOptionValue(CommandLineParam.SYMBOLS.getLongOpt());
        outputDir = cmd.getOptionValue(CommandLineParam.OUTPUTDIR.getLongOpt());
    }
}
