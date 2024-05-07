package service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpayPaymentService")
public class RazorpayPaymentService implements IPaymentService {
    private RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String doPayment(String email, Integer amount, String phoneNo, String orderId) throws RazorpayException {
        // S1. constructing data
        JSONObject requestBody = getRequestBodyForRazorPay(amount, orderId);
        // S2. call razorpay
        PaymentLink razorpayPaymentLink = razorpayClient.paymentLink.create(requestBody);

        // may be you can store this information in your database.
        // just to keep a track.

        return  razorpayPaymentLink.toString();
    }

    /**
     * {
     * "amount" :
     * "currency"
     * "receipt"
     * "customer" :{
     * "phone" : "",
     * "email : "",
     * },
     * "notify" : {
     * "sms" : true,
     * "email" : true,
     * }
     * }
     */
    private JSONObject getRequestBodyForRazorPay(Integer amount, String orderId) {
        JSONObject customerInfo = new JSONObject();
        customerInfo.put("phone", "9999999999");
        customerInfo.put("email", "yash.jain@gmail.com");

        // set some notification configurations
        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);


        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount); // m
        orderRequest.put("currency", "INR"); // m
        orderRequest.put("receipt", orderId); // optional
        orderRequest.put("customer", customerInfo);
        orderRequest.put("notify", notify);
        orderRequest.put("callback_url", "https://scaler.com/razorpay/webhook");
        orderRequest.put("callback_method", "get");
        return customerInfo;
    }
}
