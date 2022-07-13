package com.collabothon2022.pedalpower.external.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CityInfoApiStub implements CityInfoApi{

	private final List<ExchangeEntry> exchangeTable = new ArrayList<>();

	public CityInfoApiStub() {
		exchangeTable.add(new ExchangeEntry(10, "20 min ticket", readBase64Img("20minTicket.txt")));
		exchangeTable.add(new ExchangeEntry(20, "40 min ticket", readBase64Img("40minTicket.txt")));
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
	public List<ExchangeEntry> getExchangeOptionList(){
		return exchangeTable;
	}
}
