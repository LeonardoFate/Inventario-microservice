package com.proyecto.orders_service.service;

import com.proyecto.orders_service.config.WebClientConfig;
import com.proyecto.orders_service.model.dtos.BaseResponse;
import com.proyecto.orders_service.model.dtos.OrderItemRequest;
import com.proyecto.orders_service.model.dtos.OrderRequest;
import com.proyecto.orders_service.model.entities.Order;
import com.proyecto.orders_service.model.entities.OrderItems;
import com.proyecto.orders_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        //chek for inventory
       BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8084/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrdersItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
        if (result != null && !result.hasErrors()) {


            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrdersItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                    .toList());
            this.orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("failed");
        }
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder().id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }

}
