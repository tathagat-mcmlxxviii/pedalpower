package com.collabothon2022.pedalpower.external;

import com.collabothon2022.pedalpower.external.api.city.CityInfoApi;
import com.collabothon2022.pedalpower.external.api.city.CityInfoApiStub;
import com.collabothon2022.pedalpower.external.api.city.ExchangeEntry;
import com.collabothon2022.pedalpower.external.api.city.ExchangeRequest;
import com.collabothon2022.pedalpower.external.api.city.ExchangeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CityInfoApiStubTest {

	CityInfoApi testee = new CityInfoApiStub();

	@Test
	public void shouldGetExchangeOptions() {
		// when
		List<ExchangeEntry> exchangeOptionList = testee.getExchangeOptionList();
		// then
		Assertions.assertTrue(exchangeOptionList.size() > 0);
	}

	@Test
	public void shouldExecuteExchange() {
		// given
		List<ExchangeEntry> exchangeOptionList = testee.getExchangeOptionList();
		ExchangeEntry exchangeEntry = exchangeOptionList.get(0);
		// when
		ExchangeResponse exchangeResponse = testee.executeExchange(new ExchangeRequest(1, exchangeEntry.getBuyUrl()));
		// then
		Assertions.assertNotNull(exchangeResponse);
		Assertions.assertNotNull(exchangeResponse.getBase64TicketImg());

	}
}
