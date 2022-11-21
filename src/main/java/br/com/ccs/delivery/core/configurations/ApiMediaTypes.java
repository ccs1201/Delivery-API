package br.com.ccs.delivery.core.configurations;

import org.springframework.http.MediaType;

public final class ApiMediaTypes {

    public static final String V1_APPLICATION_JSON_VALUE = "application/vnd.delivery.v1+json";
    public static final MediaType V1_APPLICATION_JSON = MediaType.valueOf(V1_APPLICATION_JSON_VALUE);
    }
