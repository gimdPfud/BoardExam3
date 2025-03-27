/***********************************************
 * 클래스명 : ProductController
 * 기능 : 상품 제어를 위한 클래스.
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.controller;

import com.fileuploadexam.demo3.dto.ProductDTO;
import com.fileuploadexam.demo3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;

    /*
     * 메소드명 : productListView()
     * 인수 값 :
     * 리턴 값 : List<DTO>
     * 기  능 : 조회한 모든 데이터를 list에 전달!
     * */
    @GetMapping("/")
    public String productListView(Model model){
        List<ProductDTO> productDTOList = productService.allData();
        model.addAttribute("list", productDTOList);
        return "product/list";
    }
}
