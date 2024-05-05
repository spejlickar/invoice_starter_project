package cz.itnetwork.entity;

import cz.itnetwork.constant.Countries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long invoiceNumber;

    @Column(nullable = false)
    @ManyToOne
    private PersonEntity seller;

    @Column(nullable = false)
    @ManyToOne
    private PersonEntity buyer;

    @Column(nullable = false)
    private Date issued;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int vat;

    @Column(nullable = false)
    private String note;
}
