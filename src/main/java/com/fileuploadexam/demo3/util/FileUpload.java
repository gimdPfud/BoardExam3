/***********************************************
 * 클래스명 : FileUpload
 * 기능 : 파일업로드 관련 클래스
 * 작성자 : 김예령
 * 작성일 : 2025-03-27
 * 수정 : 2025-03-27
 * ***********************************************/
package com.fileuploadexam.demo3.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Component
public class FileUpload {
    /*
    * 메소드명 : FileUpload()
    * 인수 값 : 저장위치, 저장할 파일
    * 리턴 값 : 새로 생성된 파일 이름
    * 기  능 : 지정된 파일 이름으로 새로운 이름을 생성해서 지정한 위치에 저장
    * */
    public String FileUpload(String imgLocation, MultipartFile imgFile){
        String originalFileName = imgFile.getOriginalFilename();    //이미지파일에서 파일명을 분리함. "image.jpg"
        String extension = originalFileName
                .substring(originalFileName.lastIndexOf("."));  //확장자 분리. ".jpg"
                                                                    //substring(시작,얼마나) : 지정한 위치부터 지정한 갯수만큼 문자열을 추출
        UUID uuid = UUID.randomUUID();                              //난수로 문자를 조합해서 생성함. "asd89aasdf-a8dsf-ads8fas..."
        String saveFileName = uuid + extension;                     //새로운 파일명을 만듦. "uuid.jpg"
        String uploadUrl = imgLocation + saveFileName;              //최종 저장되는 위치 "product/image/uuid.jpg"

        /*외부작업은 반드시 try를 적용*/
        try {
            File folder = new File(imgLocation);
            if(!folder.exists()){                                   //해당 폴더가 존재하지 않으면
                boolean result = folder.mkdir();
            }
            byte[] filedata = imgFile.getBytes();                   //이미지를 바이트단위로 읽어옴.
            FileOutputStream fos = new FileOutputStream(uploadUrl); //해당위치에 파일을 연속적으로 출력한다.
            fos.write(filedata);                                    //파일 쓰기
            fos.close();                                            //쓰기가 완료되면 파일을 닫는다. (안하면 이미지 깨짐!!)
        } catch (Exception e) {
            return null;
        }
        return saveFileName;
    }

    /*
     * 메소드명 : FileDelete()
     * 인수 값 : 저장된위치, 삭제할 파일이름
     * 리턴 값 : 없음
     * 기  능 : 지정된 위치에서 파일을 삭제
     * */
    public void FileDelete(String imgLocation, String imgFileName){
        String deleteFileName = imgLocation + imgFileName ;
        try {
            File deleteFile = new File(deleteFileName);
            if(deleteFile.exists()){                                //해당파일이 존재하면
                deleteFile.delete();                                //삭제한다.
            }
        } catch (Exception e) {

        }
    }
}