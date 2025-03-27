/***********************************************
 * 클래스명 : BaseEntity
 * 기능 : 여러 테이블에서 사용하는 공통필드만 선언하는 베이스 엔티티
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass           //얘는 제공용 클래스입니다
@EntityListeners(AuditingEntityListener.class) //날짜 자동생성
public class BaseEntity {
    @CreatedDate
    @Column(name = "regDate", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "modDate")
    private LocalDateTime modDate;
}

/*1. 변수 만들기 -> 2. 변수 역할 지정하기*/