package com.dongrami.todo.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class LocalDateListConverter implements AttributeConverter<List<LocalDate>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<LocalDate> attribute) {
        return attribute.stream()
                .map(LocalDate::toString)
                .collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<LocalDate> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(SPLIT_CHAR))
                .map(LocalDate::parse)
                .collect(Collectors.toList());
    }

}