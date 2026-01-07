package DTO.req;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传请求参数
 */
@Data
@Builder
public class FileUploadReqDto {
    /**
     *  文件
     */
    private MultipartFile file;

    /**
     * 存储平台
     */
    private String platform;
}
