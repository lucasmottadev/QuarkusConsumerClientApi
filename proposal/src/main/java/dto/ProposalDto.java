package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ProposalDto {

    private Long proposalId;

    private String customer;

    private BigDecimal pricePerTonne;

}
