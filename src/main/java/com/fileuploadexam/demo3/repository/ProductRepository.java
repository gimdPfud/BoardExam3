/***********************************************
 * 인터페이스명 : ProductRepository
 * 기능 :
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.repository;

import com.fileuploadexam.demo3.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    //조회작업이 여러개일경우 메소드가 필요
}
