package com.lucasmottadev.service;

import com.lucasmottadev.client.CurrencyPriceClient;
import com.lucasmottadev.dto.CurrencyPriceDto;
import com.lucasmottadev.dto.QuotationDto;
import com.lucasmottadev.entity.QuotationEntity;
import com.lucasmottadev.message.KafkaEvents;
import com.lucasmottadev.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class QuotationService {

    @Inject
    @RestClient
    CurrencyPriceClient client;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;

    public void getCurrencyPrice() {

        CurrencyPriceDto currencyPriceInfo = client.getPriceByPair("USD-BRL");

        if (updateCurrentInfoPrice(currencyPriceInfo)) {
            kafkaEvents.sendMessageKafkaEvent(QuotationDto
                    .builder()
                    .currencyPrice(new BigDecimal(currencyPriceInfo.getUSDBRLDto().getBid()))
                    .date(new Date())
                    .build());
        }

    }

    private boolean updateCurrentInfoPrice(CurrencyPriceDto currencyPrice) {
        BigDecimal currentPrice = new BigDecimal(currencyPrice.getUSDBRLDto().getBid());

        boolean updatePrice = false;

        List<QuotationEntity> quotationList = quotationRepository.findAll().list();

        if (quotationList.isEmpty()) {

            saveQuotation(currencyPrice);

            updatePrice = true;

        } else {

            QuotationEntity lastDollarPrice = quotationList.get(quotationList.size() - 1);

            if (currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()) {

                updatePrice = true;

                saveQuotation(currencyPrice);

            }
        }

        return updatePrice;

    }

    private void saveQuotation(CurrencyPriceDto currencyPriceDto) {

        QuotationEntity quotation = new QuotationEntity();

        quotation.setDate(new Date());
        quotation.setCurrencyPrice(new BigDecimal(currencyPriceDto.getUSDBRLDto().getBid()));
        quotation.setPctChange(currencyPriceDto.getUSDBRLDto().getPctChange());
        quotation.setPctChange("USD-BRL");

        quotationRepository.persist(quotation);

    }
}
