package com.collabothon2022.pedalpower.external.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.collabothon2022.pedalpower.persistence.model.City;

@Component
public class CityInfoApiStub implements CityInfoApi {

	private static final String TICKET_20_MIN_URL = "http://city.external.api/20minTicket";
	private static final String TICKET_40_MIN_URL = "http://city.external.api/40minTicket";

	private final List<ExchangeEntry> exchangeTable = new ArrayList<>();

	private final Map<String, ExchangeResponse> mockedResponses = new HashMap<>();

	public CityInfoApiStub() {
		exchangeTable.add(new ExchangeEntry(1, 10, "20 min ticket", TICKET_20_MIN_URL));
		exchangeTable.add(new ExchangeEntry(2, 20, "40 min ticket", TICKET_40_MIN_URL));

		mockedResponses.put(TICKET_20_MIN_URL, new ExchangeResponse(readBase64Img("20minTicket.txt")));
		mockedResponses.put(TICKET_40_MIN_URL, new ExchangeResponse(readBase64Img("40minTicket.txt")));
	}

	private String readBase64Img(String filePath) {
		StringBuilder file = new StringBuilder();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
			if (is != null) {
				try (InputStreamReader isr = new InputStreamReader(is);
						BufferedReader reader = new BufferedReader(isr)) {
					String line;
					while ((line = reader.readLine()) != null) {
						file.append(line);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file.toString();
	}

	@Override
	public List<ExchangeEntry> getExchangeOptionList(City city) {
		return exchangeTable;
	}

	@Override
	public ExchangeResponse executeExchange(ExchangeRequest exchangeRequest) {
		return mockedResponses.get(exchangeRequest.getBuyUrl());
	}
}
