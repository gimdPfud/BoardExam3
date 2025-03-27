/***********************************************
 * 클래스명 : ProductEntity
 * 기능 : 상품정보를 관리하는 테이블
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "product")
@SequenceGenerator(
        name = "product_seq",
        sequenceName = "product_seq",
        initialValue = 1,
        allocationSize = 4
)
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "pid")
    private Integer pid;                        //번호

    @Column(name="product", length = 100, nullable = false)
    private String product;                     //상품명

    @Column(name="detail", length = 200)
    private String detail;                      //상품설명

    @Column(name="quantity")
    private Integer quantity=0;                 //재고량

    @Column(name="price")
    private Integer price=0;                    //가격

    @Column(name="sale")
    private Integer sale;                       //세일정보(1:정상판매, 2:세일중)

    @Column(name="state")
    private Integer state;                      //상품정보(1:신상, 2:판매중, 3:이월)

    @Column(name="img")
    private String img;                         //상품이미지
}