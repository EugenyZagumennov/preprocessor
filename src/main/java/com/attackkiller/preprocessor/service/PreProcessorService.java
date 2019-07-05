package com.attackkiller.preprocessor.service;

import com.attackkiller.preprocessor.core.exceptions.WrongSymbolException;
import com.attackkiller.preprocessor.core.parser.SymbolsParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * Service implements main logic
 *
 * @author evgenii.zagumennov
 */
public class PreProcessorService {
    private static final Logger logger = Logger.getLogger(PreProcessorService.class.getName());

    private static final Integer THREAD_NUMBER = 10;

    private Map<String, Boolean> symbols;
    private ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);

    /**
     *
     * @param cSharpDir
     * @param symbolsFile
     * @param outputDir
     */
    public void run(String cSharpDir, String symbolsFile, String outputDir){
        try {
            symbols = SymbolsParser.parse(new FileInputStream(symbolsFile));
        } catch (FileNotFoundException | WrongSymbolException e) {
            logger.error(e.getMessage());
            return;
        }

        try {
            Files.walkFileTree(Paths.get(cSharpDir), new SimpleFileVisitor<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<Path> paths = Files.walk(Paths.get(cSharpDir))) {
            paths.filter(Files::isRegularFile)
                 .forEach(path -> processFile(path.getFileName().toString()));
        }catch(IOException e){
            logger.error("Error while reading c-sharp directory ", e);
        }

    }

    private void processFile(String fileName){
        executor.submit()
    }

}
