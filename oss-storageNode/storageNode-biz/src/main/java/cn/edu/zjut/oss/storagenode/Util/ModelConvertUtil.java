package cn.edu.zjut.oss.storagenode.Util;

import cn.edu.zjut.oss.storagenode.model.DO.FilePartDetail;
import org.dromara.x.file.storage.core.FileInfo;

public class ModelConvertUtil {
    /**
     * 文件分片信息转换
     *
     * @param fileInfo
     * @param hashCode
     * @param partNumber
     * @return
     */
    public static FilePartDetail convertFilePartDetail(FileInfo fileInfo, String hashCode, Integer partNumber) {
        return FilePartDetail.builder()
                .id(fileInfo.getId())
                .filePath(fileInfo.getPath())
                .filename(fileInfo.getFilename())
                .hashInfo(hashCode)
                .partNumber(partNumber)
                .partSize(fileInfo.getSize())
                .createTime(fileInfo.getCreateTime())
                .build();
    }
}
