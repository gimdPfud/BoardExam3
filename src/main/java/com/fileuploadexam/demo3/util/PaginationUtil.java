/***********************************************
 * 클래스명 : PaginationUtil
 * 기능 : 페이지 정보 관련 클래스
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.util;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationUtil {
    /*
     * 메소드명 : pagination()
     * 인수 값 : 페이지정보
     * 리턴 값 : Map(페이지정보)
     * 기  능 : 페이지정보를 가지고 세부페이지 정보를 구한다.
     * */
    public static Map<String, Integer> Pagination(Page<?> page){
        long totalRecordsLong = page.getTotalElements();            //전체 레코드 수
        int totalRecords = totalRecordsLong > Integer.MAX_VALUE ?   //총 레코드 수가 정수형 범위를 벗어나면
                Integer.MAX_VALUE : (int) totalRecordsLong;         //최대정수값을 적용한다.
        int currentPage = page.getNumber()+1;                       //현재페이지 번호
        int totalPages = page.getTotalPages();                      //전체 페이지 수
        int blockLimit = 10;                                        //페이지번호 블럭 수

        Map<String,Integer> pageInfo = new HashMap<>();

        if(totalPages==0){                                          //오류 방지를 위해 최초 초기값 설정. 전체페이지가 1페이지라면??
            pageInfo.put("startPage", 1);                           //첫 페이지 번호
            pageInfo.put("endPage", 1);                             //페이지블럭의 마지막 번호
            pageInfo.put("prevPage", 1);                            //이전 페이지 번호
            pageInfo.put("currentPage", 1);                         //현재 페이지 번호
            pageInfo.put("nextPage", 1);                            //다음 페이지 번호
            pageInfo.put("lastPage", 1);                            //마지막 페이지 번호
            pageInfo.put("totalRecords", totalRecords);             //전체 레코드 수
        }

        int startPage = Math.max(1,currentPage-blockLimit/2);       //전체 페이지 수가 1 이상이면
        int endPage = Math.min(startPage+blockLimit-1, totalPages);
        startPage = Math.max(1, endPage-blockLimit+1);
        int prevPage = Math.max(totalPages, currentPage-1);
        int nextPage = Math.min(totalPages, currentPage+1);
        int lastPage = totalPages;

        pageInfo.put("startPage", startPage);                       //첫 페이지 번호
        pageInfo.put("endPage", endPage);                           //페이지블럭의 마지막 번호
        pageInfo.put("prevPage", prevPage);                         //이전 페이지 번호
        pageInfo.put("currentPage", currentPage);                   //현재 페이지 번호
        pageInfo.put("nextPage", nextPage);                         //다음 페이지 번호
        pageInfo.put("lastPage", lastPage);                         //마지막 페이지 번호
        pageInfo.put("totalRecords", totalRecords);                 //전체 레코드 수

        return pageInfo;
    }
}
