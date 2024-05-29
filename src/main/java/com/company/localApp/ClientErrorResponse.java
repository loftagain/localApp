package com.company.localApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

}
