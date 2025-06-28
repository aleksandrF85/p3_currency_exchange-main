package ru.skillbox.currency.exchange.client;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skillbox.currency.exchange.xml.ValCurs;
import ru.skillbox.currency.exchange.xml.Valute;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CbrClient {

    @Value("${cbr.url}")
    private String url;

    public List<Valute> fetchCurrencies() {
        try {
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            try (InputStream is = new URL(url).openStream()) {
                ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(is);
                return valCurs.getValutes();
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении валют с ЦБ РФ", e);
        }
    }
}

