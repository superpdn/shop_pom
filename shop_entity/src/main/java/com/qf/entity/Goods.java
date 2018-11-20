package com.qf.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Author pdn
 * @Time 2018/11/19 18:43
 * @Version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Goods implements Serializable{

    private Integer id;
    private String title;
    private String ginfo;
    private Integer gcount;
    private Integer tid=1;
    private Double allprice;
    private Double price;
    private String gimage;
}
