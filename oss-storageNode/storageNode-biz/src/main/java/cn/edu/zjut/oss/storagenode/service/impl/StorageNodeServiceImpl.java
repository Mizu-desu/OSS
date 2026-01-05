package cn.edu.zjut.oss.storagenode.service.impl;

import DTO.req.FilePartUploadReqDto;
import cn.edu.zjut.oss.common.util.FileHashUtil;
import cn.edu.zjut.oss.storagenode.Util.ModelConvertUtil;
import cn.edu.zjut.oss.storagenode.mapper.FilePartDetailMapper;
import cn.edu.zjut.oss.storagenode.model.DO.FilePartDetail;
import cn.edu.zjut.oss.storagenode.service.StorageNodeService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
@Slf4j
public class StorageNodeServiceImpl implements StorageNodeService {

    @Resource
    FileStorageService fileStorageService;

    @Resource
    FilePartDetailMapper filePartDetailMapper;

    @Value("${upload.path}")
    String uploadPath;

    @Override
    public void saveStorageNode(MultipartFile file) {
        FileInfo fileInfo = fileStorageService.of(file)
                .setPath(uploadPath)
                .upload();
    }

    @Override
    public void getStorageNode(String fileName) {

    }

    @Override
    public void deleteStorageNode(String fileName) {

    }

    @Override
    public void uploadPart(FilePartUploadReqDto filePartUploadReqDto) throws IOException {
        String hashCode = FileHashUtil.hashMultipartFile(filePartUploadReqDto.getFile());
        FileInfo fileInfo = fileStorageService.of(filePartUploadReqDto.getFile())
                .setPath(uploadPath)
                .setName(hashCode)
                .upload();
        FilePartDetail filePartDetail = ModelConvertUtil.convertFilePartDetail(fileInfo, hashCode, filePartUploadReqDto.getPartNumber());
        int inserted = filePartDetailMapper.insert(filePartDetail);
    }
}
