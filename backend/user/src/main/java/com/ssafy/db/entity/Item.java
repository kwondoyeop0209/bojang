package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GenericGenerator(name="Id",strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "Id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "on_sale")
    private boolean onSale;

    @CreatedDate
    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @ManyToOne
    private Price price;


    @ManyToOne
    private ItemType itemType;


    @ManyToOne
    private Store storeId;


}