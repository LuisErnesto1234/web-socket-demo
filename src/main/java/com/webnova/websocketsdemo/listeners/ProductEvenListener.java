package com.webnova.websocketsdemo.listeners;

import com.webnova.websocketsdemo.request.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEvenListener {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleProductoAgregado(ProductResponse productResponse){
        //Notificamos al cliente el nuevo producto
        simpMessagingTemplate.convertAndSend("/topic/product/nuevo", productResponse);

        log.info("Notificacion envia al frontend :)");
    }
}
