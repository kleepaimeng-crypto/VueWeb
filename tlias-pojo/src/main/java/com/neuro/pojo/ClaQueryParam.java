package com.neuro.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ClaQueryParam {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end;
    private Integer page;
    private Integer pageSize;

}
