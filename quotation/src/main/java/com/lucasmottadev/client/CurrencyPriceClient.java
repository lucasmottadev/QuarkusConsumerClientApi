package com.lucasmottadev.client;

import com.lucasmottadev.dto.CurrencyPriceDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/last")
@RestClient
@ApplicationScoped
@RegisterRestClient(baseUri = "https://economia.awesomeapi.com.br")
public interface CurrencyPriceClient {

    @GET
    @Path("/{pair}")
    CurrencyPriceDto getPriceByPair(@PathParam("pair") String pair);

}
