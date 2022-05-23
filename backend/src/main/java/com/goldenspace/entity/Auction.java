package com.goldenspace.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Table(name = "auction")
@Data
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    @CreationTimestamp
    private Date startDate;

    @Column(name = "end_date")
    @UpdateTimestamp
    private Date endDate;

    @Column(name = "initial_price")
    private BigDecimal initialPrice;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private boolean status;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    @JsonIgnore
    private List<Bid> bids;


    //add bid
    public void addBid(Bid bid) {
        //if bid is higher than current price and bid is not null
        if (bid.getPrice().compareTo(this.currentPrice) > 0 && bid.getPrice() != null) {
            this.currentPrice = bid.getPrice();
            bids.add(bid);
        }
    }

    //@Column(name = "city")
    //private City city;
    /*

     * 
     * @ManyToOne
     * 
     * @JoinColumn(ame = "user_id", nullable = false)
     * private User user;
     */

}
