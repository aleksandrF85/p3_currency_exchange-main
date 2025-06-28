package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.client.CbrClient;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;
import ru.skillbox.currency.exchange.xml.Valute;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyUpdaterService {

    private final CbrClient cbrClient;
    private final CurrencyRepository currencyRepository;

    @Scheduled(fixedRate = 60 * 60 * 1000) // каждые 60 минут
    public void updateCurrencies() {
        List<Valute> valutes = cbrClient.fetchCurrencies();

        for (Valute valute : valutes) {
            Currency existing = currencyRepository.findByIsoNumCode(valute.getNumCode());

            double value = Double.parseDouble(valute.getValue().replace(",", "."));

            if (existing != null) {
                existing.setValue(value);
                existing.setName(valute.getName());
                existing.setNominal(valute.getNominal());
                existing.setIsoNumCode(valute.getNumCode());
                if (existing.getIsoCharCode() == null) {
                    existing.setIsoCharCode(valute.getCharCode());
                }
                currencyRepository.save(existing);
            } else {
                Currency newCurrency = new Currency();
                newCurrency.setName(valute.getName());
                newCurrency.setNominal(valute.getNominal());
                newCurrency.setValue(value);
                newCurrency.setIsoCharCode(valute.getCharCode());
                newCurrency.setIsoNumCode(valute.getNumCode());
                currencyRepository.save(newCurrency);
            }
        }

        log.info("Цены валют успешно обновлены ({} валют)", valutes.size());
    }
}

