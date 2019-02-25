package com.dapo.common.dto;

/**
 * Created by dimer on 10.10.17.
 */
public class DefaultErrorDto implements ErrorDto {

    private int code = 0;
    private String descr;
    private String className;

    public DefaultErrorDto(int code) {
        this.code = code;
    }

    public DefaultErrorDto(int code, Exception ex) {
        this.code = code;
        this.descr = ex.getMessage();
    }

    public DefaultErrorDto(Exception ex) {
        this.descr = ex.getMessage();
        this.className = ex.getClass().getCanonicalName();
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getDescription() {
        return descr;
    }

    @Override
    public ErrorDto setDescription(String descr) {
        this.descr = descr;
        return this;
    }
}
