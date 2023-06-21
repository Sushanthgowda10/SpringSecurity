package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nt.service.IPurchaseOrder;

@SpringBootApplication
public class SpringSecurityUsingSpringBootMailProj04Application {

	public static void main(String[] args) {
	ApplicationContext ctx =	SpringApplication.run(SpringSecurityUsingSpringBootMailProj04Application.class, args);
	IPurchaseOrder order = ctx.getBean("purchaseService",IPurchaseOrder.class);
	try {
		String msg = order.purchase(new String[] { "shirt","trouser","watch" }, new double[] {5000,6000,70000},
				new String[] {"sushanthgowda97@gmail.com","ashik9019284229@gmail.com"});
		System.out.println(msg);
		
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	}

}
