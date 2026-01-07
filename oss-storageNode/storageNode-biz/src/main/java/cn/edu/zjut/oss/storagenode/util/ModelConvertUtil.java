package cn.edu.zjut.oss.storagenode.util;

import cn.edu.zjut.oss.common.mapper.DO.FileDetail;
import cn.edu.zjut.oss.common.mapper.DO.FilePartDetail;
import org.dromara.x.file.storage.core.FileInfo;

public class ModelConvertUtil {

    /**
     * FileInfo 转 FileDetail
     *
     * @param fileInfo
     * @return
     */
    public static FileDetail FileInfoConvertFileDetail(FileInfo fileInfo) {
        return FileDetail.builder()
                .url(fileInfo.getUrl())
                .platform(fileInfo.getPlatform())
                .path(fileInfo.getPath())
                .filename(fileInfo.getFilename())
                .size(fileInfo.getSize())
                .createTime(fileInfo.getCreateTime())
                .updateTime(fileInfo.getCreateTime())
                .build();
    }

    /**
     * FileInfo 转 FilePartDetail
     *
     * @param fileInfo
     * @return
     */
    public static FilePartDetail fileInfoConvertFilePartDetail(FileInfo fileInfo) {
        return FilePartDetail.builder()
                .url(fileInfo.getUrl())
                .platform(fileInfo.getPlatform())
                .path(fileInfo.getPath())
                .filename(fileInfo.getFilename())
                .partSize(fileInfo.getSize())
                .createTime(fileInfo.getCreateTime())
                .updateTime(fileInfo.getCreateTime())
                .build();
    }
}
