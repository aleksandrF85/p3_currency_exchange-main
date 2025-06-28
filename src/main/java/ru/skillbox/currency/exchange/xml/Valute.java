package ru.skillbox.currency.exchange.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Valute {

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Nominal")
    private long nominal;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Value")
    private String value;

    @XmlElement(name = "NumCode")
    private long numCode;

}

