package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("purchaseService")
public class PurchaseOrderImpl implements IPurchaseOrder {
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmail;

//	@Override
//	public String purchase(String[] items, double[] prices, String[] emails) throws Exception {// Calculate the bill amount 
//    double billAmt = 0.0;
//		for (double p : prices) {
//			billAmt = billAmt + p;
//		}
//		String msg = Arrays.toString(items) + " with prices" + Arrays.toString(prices) + " are purchased with BillAmount" + billAmt;
//			// send mail 
//			String status = sendMail(msg, emails);
//			return msg + "------->" + status;
//			}
//
//	private String sendMail(String msg, String[] emails) {
//		return null;
//	}

	@Override
	public String purchase(String[] items, double[] prices, String[] emails) throws Exception {
		// Calculate the bill amount 
		double billAmt = 0.0;
		for (double p : prices) {
			billAmt = billAmt + p;
			}
		String msg = Arrays.toString(items) + " with prices" + Arrays.toString(prices)+ " are purchased with BillAmount" + billAmt;
		String status = sendMail(msg, emails);
		return msg + "------->" + status;
		}

		private String sendMail(String msg, String[] emails) throws MessagingException 
		{
			MimeMessage message = sender.createMimeMessage(); // empty email message 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromEmail);
		helper.setCc(emails);
		helper.setSubject("Open it to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("CrudRepository.pptx", new FileSystemResource("C:\\Users\\sushant.gowda\\Desktop\\CrudRepository.pptx"));
		sender.send(message);
		return "mail send";
		}
		
	
}
