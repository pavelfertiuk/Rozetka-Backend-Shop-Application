package com.rozetka.service;

import com.rozetka.dto.request.PurchaseDTO;
import com.rozetka.dto.response.PurchaseResponseDTO;

public interface CheckoutService {
    PurchaseResponseDTO placeOrder(PurchaseDTO purchaseDTO);
}
