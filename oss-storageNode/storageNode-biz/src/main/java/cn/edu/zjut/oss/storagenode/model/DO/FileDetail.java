package cn.edu.zjut.oss.storagenode.model.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * 文件记录表实体类
 */
@Data
@TableName("file_detail")
public class FileDetail {

    /**
     * 文件id
     */
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
    private String filePath;

    /**
     * 创建时间
     */
    private Date createTime;
}