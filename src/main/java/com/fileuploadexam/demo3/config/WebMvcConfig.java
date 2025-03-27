/***********************************************
 * 클래스명 : WebMvcConfig
 * 기능 : 파일 업로드할때 중요함! 외부 자원을 내부에 연동하는 클래스
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 스프링부트는 resources의 자원만 이용이 가능함.
* 외부폴더 및 S3같은 클라우드 자원을 resources에 등록해서 내부자원처럼 이용
*
* 클래스 생성 : new 클래스(); => 동일한 클래스를 여러개 만들어서 사용하는것.
* 클래스 상속 : 기존 클래스에 다른 클래스를 결합해서 하나의 클래스처럼 사용함.
*               class : extends => 클레스에 메소드를 이용.
*               interface : implements => 클래스에 메소드를 변경하면서 사용
* */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}") //반복적인 값을 프로퍼티에 저장하고 사용, 값 수정은 프로퍼티만 하면 모두 적용~~
    String uploadPath;      //프로퍼티에 저장된 변수의 값을 읽어와서 저장.

    @Override               //원래 있던거를 이렇게 바꿔서 쓸거야~
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/image/**")            // c:/product/image 폴더를 /image/**라는 이름으로 내부자원으로 지정해서 사용할거다.
                .addResourceLocations(uploadPath);
    }
}

/*
* 스프링부트 버전에 따라서 설정 방법이 다름!!! 주의........ 3.2버전은 또 다르다. (우리는 3.4.4)
* */