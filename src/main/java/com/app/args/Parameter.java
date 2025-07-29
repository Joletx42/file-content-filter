package com.app.args;

public enum Parameter {
    PATH("-o"),
    PREFIX("-p"),
    MODE("-a"),
    SHORT("-s"),
    FULL("-f"),
    HELP("-h");

    private final String flag;

    private Parameter(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public static Parameter fromFlag(String flag) {
        for (Parameter param : values()) {
            if (param.flag.equals(flag)) {
                return param;
            }
        }

        return null;
    }

}
