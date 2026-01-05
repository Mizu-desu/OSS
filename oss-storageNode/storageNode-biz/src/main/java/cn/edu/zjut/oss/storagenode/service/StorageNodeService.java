package cn.edu.zjut.oss.storagenode.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageNodeService {
    /**
     *  保存文件
     * @param file
     */
    void saveStorageNode(MultipartFile file);

    /**
     *  获取文件
     * @param fileName
     */
    void getStorageNode(String fileName);

    /**
     *  删除文件
     * @param fileName
     */
    void deleteStorageNode(String fileName);
}
