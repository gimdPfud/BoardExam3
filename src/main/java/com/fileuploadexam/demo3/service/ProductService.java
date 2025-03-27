/***********************************************
 * 클래스명 : ProductService
 * 기능 : 상품처리를 위한 클래스
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.service;

import com.fileuploadexam.demo3.dto.ProductDTO;
import com.fileuploadexam.demo3.entity.ProductEntity;
import com.fileuploadexam.demo3.repository.ProductRepository;
import com.fileuploadexam.demo3.util.FileUpload;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    /*properties에서 필요한 값을 읽어온다. (작업정보)*/
    @Value("${imgUploadLocation}")
    String imgUploadLocation;

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final FileUpload fileUpload;

    /*
     * 메소드명 : allData()
     * 인수 값 :
     * 리턴 값 : List<DTO>
     * 기  능 : 모든 데이터를 조회해서 전달
     * */
    public List<ProductDTO> allData(){
        List<ProductEntity> entities = productRepository.findAll();
        List<ProductDTO> result = Arrays.asList(modelMapper.map(entities, ProductDTO[].class));
        return result;
    }

    /*
     * 메소드명 : productDelete()
     * 인수 값 : id
     * 리턴 값 :
     * 기  능 : 해당 id의 데이터 삭제
     * */
    public void productDelete(Integer id){
        productRepository.deleteById(id);
    }

    /*
     * 메소드명 : productDetail()
     * 인수 값 : id
     * 리턴 값 :
     * 기  능 : 해당 id의 데이터 조회 전달
     * */
    public ProductDTO productDetail(Integer id){
        //해당 id를 조회해서 없으면 오류 발생
        ProductEntity entity = productRepository.findById(id).orElseThrow();
        ProductDTO result = modelMapper.map(entity, ProductDTO.class);
        return result;
    }

    /*
     * 메소드명 : productSave()
     * 인수 값 : DTO, multipart
     * 리턴 값 :
     * 기  능 : 해당 상품 정보와 이미지를 저장함.
     * */
    public void productSave(ProductDTO dto, MultipartFile imgFile){
        //파일을 저장
        String newFileName = fileUpload.FileUpload(imgUploadLocation, imgFile);
        if(newFileName!=null || newFileName.length()>0){
            //저장된 파일의 새로운 이름을 dto에 갱신.
            dto.setImg(newFileName);
        }
        //변환
        ProductEntity entity = modelMapper.map(dto,ProductEntity.class);
        productRepository.save(entity);
    }

    /*
     * 메소드명 : productModify()
     * 인수 값 : DTO, multipart
     * 리턴 값 :
     * 기  능 : 해당 상품 정보와 이미지를 수정함.
     * */
    public void productModify(ProductDTO dto, MultipartFile imgFile){
        //새로운 이미지가 있으면 기존 파일 삭제. (아니면 그대로 유지)
        System.out.println("수정 : "+dto +"              "+ imgFile.getOriginalFilename());
        if(imgFile!=null && !imgFile.isEmpty()){                        //새로운 이미지파일이 존재하면
            ProductEntity entity =
                    productRepository.findById(dto.getPid())            //해당 데이터를 조회함.
                            .orElseThrow();
            String deleteFile = entity.getImg();                        //기존 이미지파일을 읽어옴
            fileUpload.FileDelete(imgUploadLocation, deleteFile);       //기존 이미지파일을 삭제함.

            String newimgFile =
                    fileUpload.FileUpload(imgUploadLocation, imgFile);  //새로운 이미지 파일을 저장
            dto.setImg(newimgFile);
        }
        //변환
        ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
        productRepository.save(entity);
    }
}
