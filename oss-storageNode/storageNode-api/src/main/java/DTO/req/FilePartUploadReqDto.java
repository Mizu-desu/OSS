package DTO.req;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件分片上传请求参数
 */
@Data
@Builder
public class FilePartUploadReqDto {
    /**
     * 文件
     */
    private MultipartFile file;

    /**
     * 分片号
     */
    private Integer partNumber;

    /**
     * 存储平台
     */
    private String platform;
}
