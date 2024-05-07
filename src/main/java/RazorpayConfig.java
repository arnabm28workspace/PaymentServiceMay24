import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key.id}")
    String razorpayId;
    @Value("${razorpay.key.secret}")
    String razorpaySecret;

}
