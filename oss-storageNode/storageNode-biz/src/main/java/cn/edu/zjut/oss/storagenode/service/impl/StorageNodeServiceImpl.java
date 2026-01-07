package cn.edu.zjut.oss.storagenode.service.impl;

import DTO.req.FileDownloadReqDto;
import DTO.req.FilePartUploadReqDto;
import DTO.req.FileUploadReqDto;
import DTO.resp.FilePartUploadRespDto;
import DTO.resp.FileUploadRespDto;
import cn.edu.zjut.oss.common.util.FileHashUtil;
import cn.edu.zjut.oss.common.constant.FileConstant;
import cn.edu.zjut.oss.common.mapper.DO.FileDetail;
import cn.edu.zjut.oss.common.mapper.DO.FilePartDetail;
import cn.edu.zjut.oss.storagenode.repository.FileDetailRepository;
import cn.edu.zjut.oss.storagenode.repository.FilePartDetailRepository;
import cn.edu.zjut.oss.storagenode.service.StorageNodeService;
import cn.edu.zjut.oss.storagenode.util.ModelConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class StorageNodeServiceImpl implements StorageNodeService {

    @Resource
    FileStorageService fileStorageService;

    @Resource
    FileDetailRepository fileDetailRepository;

    @Resource
    FilePartDetailRepository filePartDetailRepository;

    @Value("${store.local_platform}")
    private String storagePlatform;

    // 上传路径
    @Value("${store.path}")
    String uploadPath;

    // 分片上传路径
    @Value("${store.part-path}")
    String uploadPartPath;


    @Override
    public FileUploadRespDto upload(FileUploadReqDto fileUploadReqDto) throws IOException {
        FileUploadRespDto fileUploadRespDto = new FileUploadRespDto();
        // 解析文件哈希信息
        String fileHashInfo = FileHashUtil.hashMultipartFile(fileUploadReqDto.getFile());
        if (fileDetailRepository.fileISExist(fileHashInfo)) {
            // 文件已存在
            String id = fileDetailRepository.uploadExistFile(fileHashInfo);
            fileUploadRespDto.setId(id);
        } else {
            // 文件不存在
            String extension = FilenameUtils.getExtension(fileUploadReqDto.getFile().getOriginalFilename());
            String fileName = fileHashInfo + "." + extension;

            FileInfo fileInfo = fileStorageService.of(fileUploadReqDto.getFile())
                    .setPath(uploadPath)
                    .setPlatform(fileUploadReqDto.getPlatform())
                    .setName(fileName)
                    .upload();
            FileDetail fileDetail = ModelConvertUtil.FileInfoConvertFileDetail(fileInfo);
            fileDetail.setHashInfo(fileHashInfo);
            fileDetail.setIsPart(FileConstant.FILE_IS_NOT_PART);
            fileDetail.setIsDeleted(FileConstant.FILE_IS_NOT_DELETED);
            String id = fileDetailRepository.uploadUnExistFile(fileDetail);
            fileUploadRespDto.setId(id);
        }
        return fileUploadRespDto;
    }

    @Override
    public FilePartUploadRespDto uploadPart(FilePartUploadReqDto filePartUploadReqDto) throws IOException {
        FilePartUploadRespDto filePartUploadRespDto = new FilePartUploadRespDto();
        String fileHashInfo = FileHashUtil.hashMultipartFile(filePartUploadReqDto.getFile());
        if (filePartDetailRepository.filePartIsExist(fileHashInfo)) {
            // 分片已存在
            String id = filePartDetailRepository.uploadPartExistFile(fileHashInfo, filePartUploadReqDto.getPartNumber());
            filePartUploadRespDto.setId(id);
        } else {
            // 分片不存在
            FileInfo fileInfo = fileStorageService.of(filePartUploadReqDto.getFile())
                    .setPlatform(filePartUploadReqDto.getPlatform())
                    .setPath(uploadPartPath)
                    .setName(fileHashInfo)
                    .upload();
            FilePartDetail filePartDetail = ModelConvertUtil.fileInfoConvertFilePartDetail(fileInfo);
            filePartDetail.setHashInfo(fileHashInfo);
            filePartDetail.setPartNumber(filePartUploadReqDto.getPartNumber());
            filePartDetail.setIsDeleted(FileConstant.FILE_IS_NOT_DELETED);
            String id = filePartDetailRepository.uploadPartUnExistFile(filePartDetail);
            filePartUploadRespDto.setId(id);
        }
        return filePartUploadRespDto;
    }

    @Override
    public byte[] download(FileDownloadReqDto fileDownloadReqDto) throws IOException {
        if (fileDownloadReqDto.getPlatform().equals(storagePlatform)) {
            FileInfo fileInfo = new FileInfo().setPlatform(storagePlatform).setPath(fileDownloadReqDto.getPath()).setFilename(fileDownloadReqDto.getFilename());
            // 下载到 OutputStream 中
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            fileStorageService.download(fileInfo).outputStream(out);
            return out.toByteArray();
        } else {
            log.error("不支持文件存储平台:{}", fileDownloadReqDto.getPlatform());
            return null;
        }
    }


}
