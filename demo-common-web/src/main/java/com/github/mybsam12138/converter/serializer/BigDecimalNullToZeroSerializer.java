package com.github.mybsam12138.converter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalNullToZeroSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(
            BigDecimal value,
            JsonGenerator gen,
            SerializerProvider serializers
    ) throws IOException {
        if (value == null) {
            gen.writeNumber(BigDecimal.ZERO);
        } else {
            gen.writeNumber(value);
        }
    }
}