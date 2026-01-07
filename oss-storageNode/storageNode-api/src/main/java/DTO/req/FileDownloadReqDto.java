package DTO.req;

import lombok.Builder;
import lombok.Data;

/**
 * 文件下载请求参数
 */
@Data
@Builder
public class FileDownloadReqDto {

    /**
     * 文件下载地址
     */
    String url;

    /**
     * 文件存储路径
     */
    String path;

    /**
     * 文件名
     */
    String filename;

    /**
     * 文件存储平台
     */
    String platform;
}
