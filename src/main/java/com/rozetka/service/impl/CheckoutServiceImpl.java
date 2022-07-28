package com.rozetka.service.impl;

import com.rozetka.dto.request.PurchaseDTO;
import com.rozetka.dto.response.PurchaseResponseDTO;
import com.rozetka.entity.Customer;
import com.rozetka.entity.Order;
import com.rozetka.entity.OrderItem;
import com.rozetka.repository.CustomerRepository;
import com.rozetka.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponseDTO placeOrder(PurchaseDTO purchaseDTO) {

        Order order = purchaseDTO.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchaseDTO.getOrderItems();
        orderItems.forEach(order::add);

        order.setShippingAddress(purchaseDTO.getShippingAddress());

        Customer customer = purchaseDTO.getCustomer();
        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponseDTO(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
