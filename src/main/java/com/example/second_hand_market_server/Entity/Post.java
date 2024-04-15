package com.example.second_hand_market_server.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("post")
public class Post {
    @Id
    private Long id;
    @Column("user_id")
    private Long userId;
    @Column("item_description")
    private String itemDescription;
    @Column("item_name")
    private String itemName;
    @Column("price")
    private double price;
    @Column("img_url")
    private String url;
    private boolean canDelete;
}
