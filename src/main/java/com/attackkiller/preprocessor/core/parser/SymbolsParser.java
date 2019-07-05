package com.attackkiller.preprocessor.core.parser;

import com.attackkiller.preprocessor.core.exceptions.WrongSymbolException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Parser for symbols. It gets an {@java.io.InputStream}, iterates through its lines and
 * splits a string like 'SYMBOL_NAME=true' to 'SYMBOL_NAME' and 'true'
 *
 * @author evgenii.zagumennov
 */
public class SymbolsParser {
    private static final String EQ = "=";

    public static Map<String, Boolean> parse(InputStream is){
        Map<String, Boolean> result = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String[] lines = reader.lines().toArray(String[]::new);
        for(int i = 0; i< lines.length; i++){
            if(lines[i].isBlank()){
                continue;
            }

            try{
                String[] arr = lines[i].split(EQ);

                //Boolean.TRUE/Boolean.False are used to not create new Boolean object for each symbol
                result.put(arr[0].trim(), "true".equals(arr[1].trim()) ? Boolean.TRUE : Boolean.FALSE);
            } catch (Exception e) {
                throw new WrongSymbolException(i);
            }
        }

        return result;
    }
}
