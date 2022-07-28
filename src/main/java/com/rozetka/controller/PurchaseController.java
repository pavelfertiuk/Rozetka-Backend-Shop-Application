package com.rozetka.controller;

import com.rozetka.dto.request.PurchaseDTO;
import com.rozetka.dto.response.PurchaseResponseDTO;
import com.rozetka.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final CheckoutService checkoutService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public PurchaseResponseDTO placeOrder(@Valid @RequestBody PurchaseDTO purchaseDTO) {

        return checkoutService.placeOrder(purchaseDTO);
    }
}
