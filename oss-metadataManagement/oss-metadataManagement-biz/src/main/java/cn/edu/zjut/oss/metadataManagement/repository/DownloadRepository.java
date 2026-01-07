package cn.edu.zjut.oss.metadataManagement.repository;


import cn.edu.zjut.oss.common.mapper.DO.FileDetail;
import cn.edu.zjut.oss.common.mapper.DO.FilePartDetail;

import java.util.List;

public interface DownloadRepository {

    /**
     * 查找文件数据
     */
    FileDetail findFileDetail(String id);

    /**
     * 查找文件分片数据
     */
    List<FilePartDetail> findFilePartDetail(String parentId);
}
