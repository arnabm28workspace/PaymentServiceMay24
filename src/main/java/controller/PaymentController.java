package controller;

import dto.PaymentsRequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.IPaymentService;

@RestController
public class PaymentController {
    private IPaymentService razorPayService;
    private IPaymentService stripePayService;

    public PaymentController(@Qualifier("razorpayPaymentService") IPaymentService razorPayService,
                             @Qualifier("stripePaymentService") IPaymentService stripePayService) {
        this.razorPayService = razorPayService;
        this.stripePayService = stripePayService;
    }

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody PaymentsRequestDTO dto) {
        String response;
        int gatewayType = getPaymentGatewayType();
        switch (gatewayType) {
            case 1:
                response = razorPayService.doPayment(dto.getEmail(), dto.getAmount(), dto.getPhoneNo(), dto.getOrderId());
            case 2:
                response = stripePayService.doPayment(dto.getEmail(), dto.getAmount(), dto.getPhoneNo(), dto.getOrderId());
        }
        // String email,Double amount, String phoneNo, String orderId
        return null;
    }





    /*
        this method can have their own implementation with a particular logic
        Problem: out of 100 requests, only 10% of the request should go to Stripe
        else should go to razorpay.
         --> O(1)
     */
    private int getPaymentGatewayType() {
        return 1;
    }

}
