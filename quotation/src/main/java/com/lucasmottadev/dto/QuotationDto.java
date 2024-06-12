package com.lucasmottadev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Jacksonized
@Builder
@AllArgsConstructor
public class QuotationDto {

    private Date date;
    private BigDecimal currencyPrice;

}
