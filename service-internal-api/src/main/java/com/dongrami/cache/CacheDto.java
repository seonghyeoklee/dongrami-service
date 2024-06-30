package com.dongrami.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CacheDto {
    private Object key;
    private Object value;
}
