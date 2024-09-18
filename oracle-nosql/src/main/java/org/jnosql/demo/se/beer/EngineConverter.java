package org.jnosql.demo.se.beer;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.nosql.AttributeConverter;
import org.eclipse.jnosql.communication.TypeReference;
import org.eclipse.jnosql.communication.semistructured.Element;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EngineConverter implements AttributeConverter<Engine,Object> {


    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    public Object convertToDatabaseColumn(Engine attribute) {
        if (attribute == null) {
            return null;
        }
        return JSONB.fromJson(JSONB.toJson(attribute), Map.class);
    }

    @Override
    public Engine convertToEntityAttribute(Object dbData) {
        Element element = (Element) dbData;
        var elements = element.get(new TypeReference<List<Element>>() {
        }).stream().collect(Collectors.toMap(Element::name, Element::get));
        return JSONB.fromJson(JSONB.toJson(elements), Engine.class);
    }
}
