package DTO.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadReqDto {
    /**
     *  文件
     */
    private MultipartFile file;
}
