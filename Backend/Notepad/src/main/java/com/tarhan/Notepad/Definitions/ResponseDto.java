package com.tarhan.Notepad.Definitions;

import lombok.Data;

@Data
public class ResponseDto {
    String responseCode;
    String detail;

    public ResponseDto(String responseCode, String detail) {
        this.responseCode = responseCode;
        this.detail = detail;
    }
}
