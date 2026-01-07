package cn.edu.zjut.oss.metadataManagement.service;

import DTO.req.DownloadReqDTO;
import DTO.resp.DownloadRespDTO;

public interface DownloadService {

    /**
     * 下载文件
     *
     * @param reqDTO
     * @return
     */
    DownloadRespDTO download(DownloadReqDTO reqDTO);
}
