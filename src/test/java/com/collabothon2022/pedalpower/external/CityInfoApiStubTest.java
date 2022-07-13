package com.collabothon2022.pedalpower.external;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.collabothon2022.pedalpower.external.api.CityInfoApi;
import com.collabothon2022.pedalpower.external.api.CityInfoApiStub;
import com.collabothon2022.pedalpower.external.api.ExchangeEntry;
import com.collabothon2022.pedalpower.external.api.ExchangeRequest;
import com.collabothon2022.pedalpower.external.api.ExchangeResponse;
import com.collabothon2022.pedalpower.persistence.model.City;

public class CityInfoApiStubTest {

	CityInfoApi testee = new CityInfoApiStub();

	@Test
	public void shouldGetExchangeOptions() {
		// when
		City city = null; // TODO
		List<ExchangeEntry> exchangeOptionList = testee.getExchangeOptionList(city);
		// then
		Assertions.assertTrue(exchangeOptionList.size() > 0);
	}

	@Test
	public void shouldExecuteExchange() {
		// given
		City city = null; // TODO
		List<ExchangeEntry> exchangeOptionList = testee.getExchangeOptionList(city);
		ExchangeEntry exchangeEntry = exchangeOptionList.get(0);
		// when
		ExchangeResponse exchangeResponse = testee.executeExchange(new ExchangeRequest(1L, exchangeEntry.getBuyUrl()));
		// then
		Assertions.assertNotNull(exchangeResponse);
		Assertions.assertNotNull(exchangeResponse.getBase64TicketImg());

	}
}
