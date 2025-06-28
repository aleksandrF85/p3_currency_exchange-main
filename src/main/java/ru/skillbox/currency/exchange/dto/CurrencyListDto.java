package ru.skillbox.currency.exchange.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CurrencyListDto {

    List<CurrencyShortDto> currencies = new ArrayList<>();;
}
