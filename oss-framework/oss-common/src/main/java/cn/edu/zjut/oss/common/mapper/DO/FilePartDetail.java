package cn.edu.zjut.oss.common.mapper.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

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
    @TableId(type =  IdType.ASSIGN_ID)
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
    private String url;

    /**
     * 文件存储平台
     */
    private String platform;

    /**
     * 文件存储路径
     */
    private String path;

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
     * 是否被删除
     */
    private Integer isDeleted;

    /**
     * 文件分片物理存储信息克隆
     *
     * @param filePartDetail
     * @return
     */
    public static FilePartDetail clone(FilePartDetail filePartDetail) {
        return new FilePartDetailBuilder()
                .id(filePartDetail.getId())
                .platform(filePartDetail.getPlatform())
                .path(filePartDetail.getPath())
                .partNumber(filePartDetail.getPartNumber())
                .parentId(filePartDetail.getParentId())
                .url(filePartDetail.getUrl())
                .filename(filePartDetail.getFilename())
                .partSize(filePartDetail.getPartSize())
                .hashInfo(filePartDetail.getHashInfo())
                .createTime(filePartDetail.getCreateTime())
                .updateTime(filePartDetail.getUpdateTime())
                .isDeleted(filePartDetail.getIsDeleted())
                .build();
    }

}