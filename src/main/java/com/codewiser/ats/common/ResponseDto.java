package com.codewiser.ats.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ResponseDto {

    private int responseCode;
    private String responseStatus;
    private HashMap<String, Object> data;
    private String msg;
}
