package com.nt.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOperationController {
	@GetMapping("/")
	public String showHome() {

		return "home";

	}

	@GetMapping("/balance")
	public String showBalance(Map<String, Object> map) {
		map.put("account_balance", new Random().nextInt(1000000000));
		return "show_balance";

	}

	@GetMapping("/offers")
	public String showOffers() {
		return "show_offer";
	}

	@GetMapping("/loanApproval")
	public String approvalBalance(Map<String, Object> map) {
		map.put("loan_amount", new Random().nextInt(1000000000));
		return "loan_approval";
	}

	@GetMapping("/denied")
	public String accesDenied() {
		return "authorization_failed";
	}
}
