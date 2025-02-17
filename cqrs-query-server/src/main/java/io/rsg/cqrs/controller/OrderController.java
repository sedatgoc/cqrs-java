package io.rsg.cqrs.controller;

import io.rsg.cqrs.dto.OrderResponseDTO;
import io.rsg.cqrs.entity.Order;
import io.rsg.cqrs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{orderId}")
    public @ResponseBody Optional<Order> getOrder(@PathVariable("orderId") String orderId){
        return orderService.getOrder(orderId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody OrderResponseDTO badRequestHandler(HttpMessageNotReadableException e){
        return new OrderResponseDTO(e.getHttpInputMessage().toString(),false);
    }
}
