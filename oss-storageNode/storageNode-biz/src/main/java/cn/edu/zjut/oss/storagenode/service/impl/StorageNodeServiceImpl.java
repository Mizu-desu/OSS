package cn.edu.zjut.oss.storagenode.service.impl;

import DTO.req.FilePartUploadReqDto;
import cn.edu.zjut.oss.common.util.FileHashUtil;
import cn.edu.zjut.oss.storagenode.Util.ModelConvertUtil;
import cn.edu.zjut.oss.storagenode.mapper.FileDetailMapper;
import cn.edu.zjut.oss.storagenode.mapper.FilePartDetailMapper;
import cn.edu.zjut.oss.storagenode.model.DO.FileDetail;
import cn.edu.zjut.oss.storagenode.model.DO.FilePartDetail;
import cn.edu.zjut.oss.storagenode.service.StorageNodeService;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
@Slf4j
public class StorageNodeServiceImpl implements StorageNodeService {

    @Resource
    FileStorageService fileStorageService;

    @Resource
    FileDetailMapper fileDetailMapper;

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



        String hashInfo = FileHashUtil.hashMultipartFile(filePartUploadReqDto.getFile());
        FilePartDetail filePartDetail1 = filePartDetailMapper.selectOne(new LambdaQueryWrapper<FilePartDetail>().eq(FilePartDetail::getHashInfo, hashInfo));
        if (filePartDetail1 == null) {

        } else {

        }

    }

    private void uploadExistPart(String parentId, Integer partNumber, FilePartDetail filePartDetail) {
        // 获取文件分片物理存储信息
        FilePartDetail newFilePartDetail = filePartDetail.filePartStoreInfoClone(filePartDetail);
        // 父类ID
        newFilePartDetail.setParentId(parentId);
        // 当前父类下分片
        newFilePartDetail.setPartNumber(partNumber);
        // 插入
        int inserted = filePartDetailMapper.insert(newFilePartDetail);
        if (inserted < 0) {
            log.error("物理文件索引插入失败:{}", newFilePartDetail);
        }
    }

    private void uploadUnexistPart(FilePartUploadReqDto filePartUploadReqDto, String fileName) {
        FileInfo fileInfo = fileStorageService.of(filePartUploadReqDto.getFile())
                .setPath(uploadPath)
                .setName(fileName)
                .upload();
        FilePartDetail filePartDetail = FilePartDetail.convertFilePartDetail(fileInfo, fileName, filePartUploadReqDto.getParentId(), filePartUploadReqDto.getPartNumber());
        int inserted = filePartDetailMapper.insert(filePartDetail);
        if (inserted < 0) {
            log.error("插入新的物理存储文件失败:{}", filePartDetail);
        }
    }
}
