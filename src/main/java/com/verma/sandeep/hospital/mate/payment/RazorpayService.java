package com.verma.sandeep.hospital.mate.payment;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.verma.sandeep.hospital.mate.constant.PaymentStatus;
import com.verma.sandeep.hospital.mate.entity.PaymentDetail;
import com.verma.sandeep.hospital.mate.entity.SlotRequest;
import com.verma.sandeep.hospital.mate.service.impl.IPaymentDetailService;
import com.verma.sandeep.hospital.mate.service.impl.SlotRequestService;

@Service
public class RazorpayService {
	
	@Autowired
	private SlotRequestService slotRequestService;
	@Autowired
	private IPaymentDetailService pymntDetailService;
	
	private final RazorpayClient razorpayClient;

    @Value("${razorpay_key_id}")
    private String keyId;

    @Value("${razorpay_secret_key}")
    private String keySecret;

    public RazorpayService() throws RazorpayException {
        this.razorpayClient = new RazorpayClient(keyId, keySecret);
    }
    
    public Map<String, Object> createOrder(Long slotRequestId) throws RazorpayException {
    	
    	SlotRequest slotRequest=slotRequestService.getOneSlotRequest(slotRequestId);
    	// Extract Patient and Appointment Details
    	String patientName=slotRequest.getPatient().getFirstName()+" "+slotRequest.getPatient().getLastName();
    	 String patientEmail =slotRequest.getPatient().getEmail();
    	 double amount=slotRequest.getAppointment().getAmt();
    	 String currency = "INR"; // Default Currency
    	 
    	// Create Razorpay Order Request
         JSONObject orderRequest = new JSONObject();
         orderRequest.put("amount", (int) (amount * 100)); // Convert to paisa
         orderRequest.put("currency", currency);
         orderRequest.put("receipt", "RCPT_" + slotRequestId);
         orderRequest.put("payment_capture", 1);
         
         //Set payment status paid
         slotRequest.setPaymentStatus(PaymentStatus.PAID.name());
         
      // Create Order in Razorpay
         Order order = razorpayClient.orders.create(orderRequest);
         String orderId = order.get("id");
         
      // Save Payment Details in Database
         PaymentDetail paymentDetail = new PaymentDetail();
         paymentDetail.setOrderId(orderId);
         paymentDetail.setAmount(amount);
         paymentDetail.setCurrency(currency);
         paymentDetail.setReceipt("RCPT_" + slotRequestId);
         paymentDetail.setPatientName(patientName);
         paymentDetail.setPatientEmail(patientEmail);
         paymentDetail.setDateOfPayment(LocalDateTime.now());
         
         pymntDetailService.savePaymentDetail(paymentDetail);
         
      // Prepare Response
         Map<String, Object> response = new HashMap<>();
         response.put("orderId", orderId);
         response.put("amount", amount);
         response.put("currency", currency);
         response.put("key", keyId);
         response.put("patientName", patientName);
         response.put("patientEmail", patientEmail);

         return response;
    	
    	
    }

}
