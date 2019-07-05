package com.attackkiller.preprocessor.core.domain;

import lombok.Getter;

@Getter
public enum Directive {
    IF("if"),
    ELSE("else"),
    ELIF("elif"),
    ENDIF("endif");

    public static final String KEY_SYMBOL = "#";

    Directive(String name) {
        this.name = name;
    }

    private String name;
}
