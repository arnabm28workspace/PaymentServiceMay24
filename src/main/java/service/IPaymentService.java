package service;

public interface IPaymentService {

    String doPayment(String email,Integer amount, String phoneNo, String orderId);
}
