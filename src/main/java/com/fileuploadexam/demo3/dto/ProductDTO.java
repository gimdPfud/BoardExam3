/***********************************************
 * 클래스명 : ProductDTO
 * 기능 :
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Integer pid;                        //번호
    private String product;                     //상품명
    private String detail;                      //상품설명
    private Integer quantity;                 //재고량
    private Integer price;                    //가격
    private Integer sale;                       //세일정보(1:정상판매, 2:세일중)
    private Integer state;                      //상품정보(1:신상, 2:판매중, 3:이월)
    private String img;                         //상품이미지

    private LocalDateTime regDate;              //등록일
    private LocalDateTime modDate;              //수정일
}
