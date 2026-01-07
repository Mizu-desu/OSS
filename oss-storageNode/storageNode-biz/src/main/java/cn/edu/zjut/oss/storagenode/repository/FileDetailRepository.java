package cn.edu.zjut.oss.storagenode.repository;

import cn.edu.zjut.oss.common.mapper.DO.FileDetail;

public interface FileDetailRepository {


    /**
     * 判断文件是否存在
     * @param fileHashInfo
     * @return
     */
    Boolean fileISExist(String fileHashInfo);

    /**
     * 上传已存在的文件
     * @param fileHashInfo 文件哈希信息
     * @return 文件id
     */
    String uploadExistFile(String fileHashInfo);

    /**
     * 上传不存在的文件
     */
    String uploadUnExistFile(FileDetail fileDetail);

}

