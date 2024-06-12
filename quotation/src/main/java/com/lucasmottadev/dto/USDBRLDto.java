package com.lucasmottadev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

// Dto that is equal to response by api
@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class USDBRLDto {
    public String code;
    public String codein;
    public String name;
    public String high;
    public String low;
    public String varBid;
    public String pctChange;
    public String bid;
    public String ask;
    public String timestamp;
    public String create_date;

}
