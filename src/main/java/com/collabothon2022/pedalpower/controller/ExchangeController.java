package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.external.api.city.CityInfoApi;
import com.collabothon2022.pedalpower.external.api.city.CityInfoApiStub;
import com.collabothon2022.pedalpower.external.api.city.ExchangeEntry;
import com.collabothon2022.pedalpower.external.api.city.ExchangeRequest;
import com.collabothon2022.pedalpower.external.api.city.ExchangeResponse;
import com.collabothon2022.pedalpower.persistence.model.User;
import com.collabothon2022.pedalpower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/exchange")
public class ExchangeController {

	private final CityInfoApi cityInfoApi;
	private UserService userService;

	@Autowired
	public ExchangeController(UserService userService) {
		this.userService = userService;
		cityInfoApi = new CityInfoApiStub();
	}

	@GetMapping(value = "list", produces = "application/json")
	public List<ExchangeEntry> getExchangeOptionList() {
		// TODO - use city instead of null
		return cityInfoApi.getExchangeOptionList(null);
	}

	@PostMapping(produces = "application/json")
	public ExchangeResponse exchange(@RequestBody BuyRequest buyRequest) {
		User user = userService.get(buyRequest.getEmail());
		ExchangeEntry buyOption = cityInfoApi.getExchangeOptionList(null).stream()
				.filter(exchangeEntry -> exchangeEntry.getBuyUrl().equalsIgnoreCase(buyRequest.getBuyUrl())).findFirst()
				.orElseThrow(() -> new IllegalStateException("unknown buy option"));

		return userService.buy(user, buyOption);
	}

	static class BuyRequest {
		private String email;
		private String buyUrl;

		public BuyRequest(String email, String buyUrl) {
			this.email = email;
			this.buyUrl = buyUrl;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getBuyUrl() {
			return buyUrl;
		}

		public void setBuyUrl(String buyUrl) {
			this.buyUrl = buyUrl;
		}
	}

}
