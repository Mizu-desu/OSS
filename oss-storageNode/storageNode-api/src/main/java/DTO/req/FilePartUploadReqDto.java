package DTO.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FilePartUploadReqDto {
    /**
     * 父级文件id
     */
    private String parentId;
    /**
     * 文件
     */
    private MultipartFile file;
    /**
     * 分片号
     */
    private Integer partNumber;
}
