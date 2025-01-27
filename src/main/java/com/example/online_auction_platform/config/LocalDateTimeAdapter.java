package com.example.online_auction_platform.config;

import java.io.IOException;
import java.time.LocalDateTime;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        // You can customize the format here (e.g., ISO 8601)
        out.value(value.toString());
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        // You can implement deserialization logic here if needed
        return LocalDateTime.parse(in.nextString());
    }

}