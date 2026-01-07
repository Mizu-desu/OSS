package api;

import DTO.req.FileDownloadReqDto;
import DTO.req.FileUploadReqDto;
import DTO.resp.FileUploadRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@FeignClient(name = "storageNode")
public interface StorageNodeFeignClient {
    String PREFIX = "/storageNode";

    @PostMapping(PREFIX + "/upload/all")
    FileUploadRespDto upload(FileUploadReqDto fileUploadReqDto) throws IOException;

    @PostMapping(PREFIX + "/upload/part")
    FileUploadRespDto uploadPart(FileUploadReqDto fileUploadReqDto) throws IOException;

    @PostMapping(PREFIX + "/download")
    ResponseEntity<byte []> download(FileDownloadReqDto fileDownloadReqDto) throws IOException;
}
