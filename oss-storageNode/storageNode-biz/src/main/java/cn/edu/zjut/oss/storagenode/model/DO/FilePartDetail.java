package cn.edu.zjut.oss.storagenode.model.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.dromara.x.file.storage.core.FileInfo;

import java.util.Date;

/**
 * 文件分片信息表，仅在手动分片上传时使用
 */
@Data
@Builder
@TableName("file_part_detail")
public class FilePartDetail {

    /**
     * 分片id
     */
    private String id;

    /**
     * 分片号。每一个上传的分片都有一个分片号，一般情况下取值范围是1～10000
     */
    private Integer partNumber;

    /**
     * 父级文件id
     */
    private String parentId;

    /**
     * 存储路径
     */
    private String filePath;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 文件大小，单位字节
     */
    private Long partSize;

    /**
     * 哈希信息
     */
    private String hashInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 文件分片物理存储信息克隆
     *
     * @param filePartDetail
     * @return
     */
    public FilePartDetail filePartStoreInfoClone(FilePartDetail filePartDetail) {
        return new FilePartDetailBuilder()
                .filePath(filePartDetail.getFilePath())
                .filename(filePartDetail.getFilename())
                .partSize(filePartDetail.getPartSize())
                .hashInfo(filePartDetail.getHashInfo())
                .createTime(filePartDetail.getCreateTime())
                .build();
    }

    /**
     * FileInfo 转 FilePartDetail
     *
     * @param fileInfo
     * @param hashInfo
     * @param partNumber
     * @return
     */
    public static FilePartDetail convertFilePartDetail(FileInfo fileInfo, String hashInfo, String parentId, Integer partNumber) {
        return FilePartDetail.builder()
                .id(fileInfo.getId())
                .filePath(fileInfo.getPath())
                .filename(fileInfo.getFilename())
                .hashInfo(hashInfo)
                .parentId(parentId)
                .partNumber(partNumber)
                .partSize(fileInfo.getSize())
                .createTime(fileInfo.getCreateTime())
                .updateTime(new Date())
                .build();
    }
}