package com.lucasmottadev.message;

import com.lucasmottadev.dto.QuotationDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("quotation-channel")
    Emitter<QuotationDto> quotationRequestEmitter;

    public void sendMessageKafkaEvent(QuotationDto quotationDto) {

        LOG.info("Sending message to Kafka");
        quotationRequestEmitter.send(quotationDto).toCompletableFuture().join();

    }
}
