package com.tvh.bootcamp.testingbootcamp.ordering.api;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvh.bootcamp.testingbootcamp.ordering.domain.Order;
import com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderInteractor;
import com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderNotFoundException;
import com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderInteractor orderInteractor;

    public OrderController(OrderService orderInteractor) {
        this.orderInteractor = orderInteractor;
    }

    @PostMapping("/")
    public ResponseEntity<OrderResource> createOrder(HttpServletRequest request, OrderResource orderResource) {
        Order createdOrder = this.orderInteractor.createOrUpdate(orderResource.toOrder());

        return ResponseEntity.created(orderLocationURI(request, createdOrder.getOrderId())).build();
    }

    private URI orderLocationURI(HttpServletRequest request, UUID orderId) {
        return URI.create(String.format("%s://%s:%d%s%s", request.getScheme(), request.getServerName(), request.getServerPort(), request.getRequestURI(), orderId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResource> getOrder(@PathVariable String orderId) {
        OrderResource orderResource = OrderResource.fromOrder(this.orderInteractor.find(UUID.fromString(orderId)));

        return ResponseEntity.ok(orderResource);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderResource orderResource) {
        this.orderInteractor.createOrUpdate(orderResource.toOrder());

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<ErrorResource> handleOrderNotFoundException(OrderNotFoundException exception, HttpServletRequest request) {
        LOGGER.info(exception.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(from(request, NOT_FOUND, exception));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResource> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        LOGGER.info(exception.getMessage());

        return ResponseEntity.status(BAD_REQUEST).body(from(request, BAD_REQUEST, exception));
    }

    private ErrorResource from(HttpServletRequest request, HttpStatus status, Exception exception) {
        String trace = Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).reduce("", String::concat);

        return new ErrorResource(LocalDateTime.now(), status.value(), status.getReasonPhrase(), exception.getMessage(), trace, request.getRequestURI());
    }
}
