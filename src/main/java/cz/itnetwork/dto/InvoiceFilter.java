package cz.itnetwork.dto;

import lombok.Data;

@Data
public class InvoiceFilter {
    private String buyerID;
    private String sellerID;
    private String product;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer limit = 20;
}
