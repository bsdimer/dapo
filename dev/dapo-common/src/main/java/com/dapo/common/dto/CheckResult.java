package com.dapo.common.dto;

/**
 * Created by dimer on 06.04.17.
 */
public class CheckResult {
    private Boolean value;
    private String[] suggestions;

    public CheckResult() {
    }

    public CheckResult(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String[] getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String[] suggestions) {
        this.suggestions = suggestions;
    }

    public static CheckResult getResult(Boolean value) {
        return new CheckResult(value);
    }

}
