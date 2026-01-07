package cn.edu.zjut.oss.storagenode.repository.impl;

import cn.edu.zjut.oss.common.constant.FileConstant;
import cn.edu.zjut.oss.common.mapper.FileDetailMapper;
import cn.edu.zjut.oss.common.mapper.DO.FileDetail;
import cn.edu.zjut.oss.storagenode.repository.FileDetailRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class FileDetailRepositoryImpl implements FileDetailRepository {
    @Resource
    FileDetailMapper fileDetailMapper;

    @Override
    public Boolean fileISExist(String fileHashInfo) {
        LambdaQueryWrapper<FileDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileDetail::getHashInfo, fileHashInfo)
                .eq(FileDetail::getIsDeleted, FileConstant.FILE_IS_NOT_DELETED);
        return fileDetailMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public String uploadExistFile(String fileHashInfo) {
        LambdaQueryWrapper<FileDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileDetail::getHashInfo, fileHashInfo)
                .eq(FileDetail::getIsDeleted, FileConstant.FILE_IS_NOT_DELETED);
        // 获取原始文件索引信息
        FileDetail fileDetail = fileDetailMapper.selectOne(queryWrapper);
        // 构建新的文件索引信息
        FileDetail newFileDetail = FileDetail.clone(fileDetail);
        newFileDetail.setId(null);
        newFileDetail.setCreateTime(new Date());
        newFileDetail.setUpdateTime(new Date());
        int updated = fileDetailMapper.insert(newFileDetail);
        if (updated == 0) {
            log.error("插入新的物理存储文件索引失败:{}", newFileDetail);
            return null;
        } else {
            return newFileDetail.getId();
        }

    }

    @Override
    public String uploadUnExistFile(FileDetail fileDetail) {
        int inserted = fileDetailMapper.insert(fileDetail);
        if (inserted == 0) {
            log.error("插入新的物理存储文件失败:{}", fileDetail);
            return null;
        } else {
            return fileDetail.getId();
        }
    }
}
