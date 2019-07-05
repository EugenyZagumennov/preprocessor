package com.attackkiller.preprocessor.core.exceptions;

public class WrongSymbolException extends RuntimeException {
    public WrongSymbolException(int lineNum) {
        super("Wrong symbol in line #"+lineNum);
    }
}
