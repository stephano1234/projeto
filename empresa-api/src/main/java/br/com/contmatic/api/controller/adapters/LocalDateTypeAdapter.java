package br.com.contmatic.api.controller.adapters;

import java.io.IOException;

import org.joda.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
	
	@Override
	public void write(JsonWriter out, LocalDate value) throws IOException {
		out.value(value.toString("yyyy-MM-dd"));	
	}

	@Override
	public LocalDate read(JsonReader in) throws IOException {
		String dateString = in.nextString();
		return !dateString.isEmpty() ? LocalDate.parse(dateString) : null;
	}

}
