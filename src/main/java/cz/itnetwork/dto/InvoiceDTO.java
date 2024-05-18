package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long invoiceNumber;

    private PersonDTO seller;

    private PersonDTO buyer;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date issued;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    private String product;

    private Integer price;

    private Integer vat;

    private String note;
}
