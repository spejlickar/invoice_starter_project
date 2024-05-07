package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long invoiceNumber;

    @ManyToOne
    private PersonEntity seller = new PersonEntity();

    @ManyToOne
    private PersonEntity buyer= new PersonEntity();

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

    public Long getSellerId(){
        if (this.seller.getId() == null) {
            return null;
        }
        return this.seller.getId();
    }

    public Long getBuyerId(){
        if (this.buyer.getId() == null) {
            return null;
        }
        return this.buyer.getId();
    }
}
