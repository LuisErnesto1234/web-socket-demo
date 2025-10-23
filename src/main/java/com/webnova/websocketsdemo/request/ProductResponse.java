package com.webnova.websocketsdemo.request;

import java.math.BigDecimal;

public record ProductResponse(Long id,
                              String nombre,
                              BigDecimal price) {
}
