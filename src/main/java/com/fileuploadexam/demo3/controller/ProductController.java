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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    /*
     * 메소드명 : productListView()
     * 인수 값 :
     * 리턴 값 : List<DTO>
     * 기  능 : 조회한 모든 데이터를 list에 전달!
     * */
    @GetMapping(value = {"/","/list"})
    public String productListView(Model model){
        List<ProductDTO> productDTOList = productService.allData();
        model.addAttribute("list", productDTOList);
        return "product/list";
    }


    /*
     * 메소드명 : productInsertView()
     * 인수 값 :
     * 리턴 값 :
     * 기  능 : 등록페이지로 이동함.
     * */
    @GetMapping("/insert")
    public String productInsertView(){
        return "product/insert";
    }
    /*
     * 메소드명 : productInsertProc()
     * 인수 값 : DTO, Multipart
     * 리턴 값 :
     * 기  능 : 받아온 dto와 파일을 저장하고 목록페이지로 이동.
     * */
    @PostMapping("/insert")
    public String productInsertProc(ProductDTO dto, MultipartFile imgFile){
        productService.productSave(dto, imgFile);
        return "redirect:/product/list";
    }


    /*
     * 메소드명 : productDeleteProc()
     * 인수 값 : id
     * 리턴 값 :
     * 기  능 : 해당 id로 데이터를 삭제하고 목록페이지로 이동.
     * */
    @GetMapping("/delete")
    public String productDeleteProc(Integer pid){
        productService.productDelete(pid);
        return "redirect:/product/list";
    }


    /*
     * 메소드명 : productDetailView()
     * 인수 값 : id
     * 리턴 값 : DTO
     * 기  능 : 해당 id로 데이터를 조회하고 상세페이지에 전달.
     * */
    @GetMapping("/detail")
    public String productDetailView(Integer pid, Model model){
        ProductDTO dto = productService.productDetail(pid);
        model.addAttribute("data",dto);
        return "product/detail";
    }


    /*
     * 메소드명 : productModifyView()
     * 인수 값 : id
     * 리턴 값 : DTO
     * 기  능 : 해당 id로 데이터를 조회하고 수정페이지에 전달.
     * */
    @GetMapping("/modify")
    public String productModifyView(Integer pid, Model model){
        ProductDTO dto = productService.productDetail(pid);
        model.addAttribute("data",dto);
        return "product/modify";
    }
    /*
     * 메소드명 : productModifyProc()
     * 인수 값 : DTO, Multipart
     * 리턴 값 :
     * 기  능 : 수정된 정보와 이미지를 저장하고 목록페이지로 이동.
     * */
    @PostMapping("/modify")
    public String productModifyProc(ProductDTO dto, MultipartFile imgFile){
        productService.productModify(dto, imgFile);
        return "redirect:/product/list";
    }
}

/*
* 틀이 바뀔 부분 없다.
* rest방식을 사용할 때는 삽입,수정 부분에서 for, if문을 사용
*   rest를 적용하는 메소드에 try를 사용해야함.
*   Controller : 요청 들어오면 응답(데이터값, 이동페이지)    --> 요청페이지와 응답페이지가 다르게 작업이 가능
*   restController : 요청 들어오면 응답(데이터값, 성공여부)  --> 요청페이지와 응답페이지가 동일
* */