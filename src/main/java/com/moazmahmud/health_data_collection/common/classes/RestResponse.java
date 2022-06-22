package com.moazmahmud.health_data_collection.common.classes;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class RestResponse {
    @Builder.Default
    private boolean success = true;
    private String error;
    @Builder.Default
    private LocalDateTime responseTime = LocalDateTime.now();
    private Object payload;
}
