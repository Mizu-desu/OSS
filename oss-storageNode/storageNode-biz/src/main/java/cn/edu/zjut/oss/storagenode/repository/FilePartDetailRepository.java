package cn.edu.zjut.oss.storagenode.repository;

import cn.edu.zjut.oss.common.mapper.DO.FilePartDetail;

public interface FilePartDetailRepository {

    /**
     * 文件分片信息是否存在
     */
    Boolean filePartIsExist(String hashInfo);

    /**
     * 上传已存在的文件分片信息
     * @param fileHashInfo
     * @return
     */
    String uploadPartExistFile(String fileHashInfo, Integer partNumber);

    /**
     * 上传不存在的文件分片信息
     * @param filePartDetail
     */
    String uploadPartUnExistFile(FilePartDetail filePartDetail);
}
