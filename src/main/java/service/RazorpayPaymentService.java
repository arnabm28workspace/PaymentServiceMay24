package service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpayPaymentService")
public class RazorpayPaymentService implements IPaymentService{
    @Override
    public String doPayment(String email, Integer amount, String phoneNo, String orderId) {
        JSONObject requestBody =  getRequestBodyForRazorPay(amount, orderId);



        return null;
    }

    /**
     * {
     *     "amount" :
     *     "currency"
     *     "receipt"
     *     "customer" :{
     *         "phone" : "",
     *         "email : "",
     *     },
     *     "notify" : {
     *         "sms" : true,
     *         "email" : true,
     *     }
     * }
     *
     *
     */
    private JSONObject getRequestBodyForRazorPay(Integer amount, String orderId) {
        JSONObject customerInfo = new JSONObject();
        customerInfo.put("phone","9999999999");
        customerInfo.put("email","yash.jain@gmail.com");

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
        return customerInfo;
    }
}
