package cn.edu.zjut.oss.metadataManagement.service.impl;

import DTO.req.DownloadReqDTO;
import DTO.req.FileDownloadReqDto;
import DTO.resp.DownloadRespDTO;
import api.StorageNodeFeignClient;
import cn.edu.zjut.oss.common.constant.FileConstant;
import cn.edu.zjut.oss.common.mapper.DO.FileDetail;
import cn.edu.zjut.oss.metadataManagement.repository.DownloadRepository;
import cn.edu.zjut.oss.metadataManagement.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class DownloadServiceImpl implements DownloadService {

    @Resource
    private DownloadRepository downloadRepository;

    @Resource
    private StorageNodeFeignClient storageNodeFeignClient;


    // 创建线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public DownloadRespDTO download(DownloadReqDTO reqDTO) {
        FileDetail fileDetail = downloadRepository.findFileDetail(reqDTO.getId());
        // 判断是否是分片文件
        if (fileDetail.getIsPart().equals(FileConstant.FILE_IS_PART)) {

        } else {

            FileDownloadReqDto fileDownloadReqDto = FileDownloadReqDto.builder()
                    .url(fileDetail.getUrl())
                    .path(fileDetail.getPath())
                    .filename(fileDetail.getFilename())
                    .platform(fileDetail.getPlatform())
                    .build();


        }
    }


}
