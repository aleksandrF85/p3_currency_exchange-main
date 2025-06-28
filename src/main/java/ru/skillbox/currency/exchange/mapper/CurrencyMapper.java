package ru.skillbox.currency.exchange.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.dto.CurrencyListDto;
import ru.skillbox.currency.exchange.dto.CurrencyShortDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto convertToDto(Currency currency);

    Currency convertToEntity(CurrencyDto currencyDto);

    CurrencyShortDto convertToCurrencyShortDto (Currency currency);

    default CurrencyListDto convertToCurrencyListDto(List<Currency> currencies) {
        CurrencyListDto response = new CurrencyListDto();

        response.setCurrencies(currencies.stream()
                .map(this::convertToCurrencyShortDto).collect(Collectors.toList()));

        return response;
    }
}
