package com.dapo.common.dto;

/**
 * Created by dimer on 10.10.17.
 */
public interface ErrorDto {
    int getCode();

    String getClassName();

    String getDescription();

    ErrorDto setDescription(String descr);
}
