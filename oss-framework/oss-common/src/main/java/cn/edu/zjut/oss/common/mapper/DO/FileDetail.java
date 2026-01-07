package cn.edu.zjut.oss.common.mapper.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 文件记录表实体类
 */
@Data
@TableName("file_detail")
@Builder
public class FileDetail {

    /**
     * 文件id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 文件大小，单位字节
     */
    private Long size;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 是否被删除
     */
    private Integer isDeleted;

    /**
     * 文件是否被分片
     */
    private Integer isPart;

    /**
     * 文件哈希信息
     */
    private String hashInfo;

    /**
     * 文件路径
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 克隆方法
     */
    public static FileDetail clone(FileDetail fileDetail) {
        return FileDetail.builder()
                .id(fileDetail.getId())
                .url(fileDetail.getUrl())
                .platform(fileDetail.getPlatform())
                .path(fileDetail.getPath())
                .filename(fileDetail.getFilename())
                .hashInfo(fileDetail.getHashInfo())
                .size(fileDetail.getSize())
                .isDeleted(fileDetail.getIsDeleted())
                .isPart(fileDetail.getIsPart())
                .createTime(fileDetail.getCreateTime())
                .updateTime(fileDetail.getUpdateTime())
                .build();
    }

}