package cn.edu.zjut.oss.storagenode.service;

import DTO.req.FileDownloadReqDto;
import DTO.req.FilePartUploadReqDto;
import DTO.req.FileUploadReqDto;
import DTO.resp.FilePartUploadRespDto;
import DTO.resp.FileUploadRespDto;

import java.io.IOException;

public interface StorageNodeService {

    /**
     * 上传文件
     * @param fileUploadReqDto
     */
    FileUploadRespDto upload(FileUploadReqDto  fileUploadReqDto) throws IOException;


    /**
     *  上传分片
     * @param filePartUploadReqDto
     */
    FilePartUploadRespDto uploadPart(FilePartUploadReqDto filePartUploadReqDto) throws IOException;

    /**
     * 下载文件
     * @param fileDownloadReqDto
     */
    byte [] download(FileDownloadReqDto fileDownloadReqDto) throws IOException;





}
