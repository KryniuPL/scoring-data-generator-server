package com.scoring.application.utils;

import com.scoring.infrastructure.web.model.DataGenerationRequest;

public class RequestHolder {

    private static DataGenerationRequest DATA_GENERATION_REQUEST = null;

    public static DataGenerationRequest getDataGenerationRequest() {
        return DATA_GENERATION_REQUEST;
    }

    public static void setDataGenerationRequest(DataGenerationRequest dataGenerationRequest) {
        DATA_GENERATION_REQUEST = dataGenerationRequest;
    }

    public static void clearDataGenerationRequest() {
        DATA_GENERATION_REQUEST = null;
    }
}
