package com.attackkiller.preprocessor.core.domain;

import lombok.Getter;

/**
 * Supported command line parameters
 *
 * @author evgenii.zagumennov
 */
@Getter
public enum CommandLineParam {
    CSHARPDIR("cd", "csharpdir", true, "directory with c-sharp files"),
    SYMBOLS("s", "symbols", true, "defined symbols"),
    OUTPUTDIR("od", "outputdir", true, "output directory");

    CommandLineParam(String opt, String longOpt, boolean hasArg, String description) {
        this.opt = opt;
        this.longOpt = longOpt;
        this.hasArg = hasArg;
        this.description = description;
    }

    private String opt;
    private String longOpt;
    private boolean hasArg;
    private String description;
}
