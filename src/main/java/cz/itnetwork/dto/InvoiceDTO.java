package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    @JsonProperty("_id")
    private Long id;

    private long invoiceNumber;

    private PersonDTO seller;

    private PersonDTO buyer;

    private Date issued;

    private Date dueDate;

    private String product;

    private int price;

    private int vat;

    private String note;
}
