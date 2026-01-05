package cn.edu.zjut.oss.storagenode.service.impl;

import cn.edu.zjut.oss.storagenode.service.FileDetailService;
import cn.edu.zjut.oss.storagenode.service.StorageNodeService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.recorder.FileRecorder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageNodeServiceImpl implements StorageNodeService {

    @Resource
    FileStorageService fileStorageService;

    @Resource
    FileRecorder  fileRecorder;

    @Value("${upload.path}")
    String uploadPath;

    @Override
    public void saveStorageNode(MultipartFile file) {
        FileInfo fileInfo = fileStorageService.of(file)
                .setPath(uploadPath)
                .upload();
        fileRecorder.save(fileInfo);
    }

    @Override
    public void getStorageNode(String fileName) {

    }

    @Override
    public void deleteStorageNode(String fileName) {

    }
}
