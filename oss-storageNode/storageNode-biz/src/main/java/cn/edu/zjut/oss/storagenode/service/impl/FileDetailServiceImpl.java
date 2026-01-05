package cn.edu.zjut.oss.storagenode.service.impl;

import cn.edu.zjut.oss.storagenode.mapper.FileDetailMapper;
import cn.edu.zjut.oss.storagenode.model.DO.FileDetail;
import cn.edu.zjut.oss.storagenode.service.FileDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.recorder.FileRecorder;
import org.dromara.x.file.storage.core.upload.FilePartInfo;
import org.springframework.stereotype.Service;

@Service
public class FileDetailServiceImpl extends ServiceImpl<FileDetailMapper, FileDetail> implements FileRecorder, FileDetailService {
    @Override
    public boolean save(FileInfo fileInfo) {
        return false;
    }

    @Override
    public void update(FileInfo fileInfo) {
    }

    @Override
    public FileInfo getByUrl(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public void saveFilePart(FilePartInfo filePartInfo) {

    }

    @Override
    public void deleteFilePartByUploadId(String s) {

    }
}
