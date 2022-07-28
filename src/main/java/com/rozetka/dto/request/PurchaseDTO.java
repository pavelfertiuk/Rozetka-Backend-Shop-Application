package com.rozetka.dto.request;

import com.rozetka.entity.Address;
import com.rozetka.entity.Customer;
import com.rozetka.entity.OrderItem;
import com.rozetka.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    @NotNull
    private Customer customer;

    @NotNull
    private Address shippingAddress;

    @NotNull
    private Order order;

    private Set<OrderItem> orderItems;
}
