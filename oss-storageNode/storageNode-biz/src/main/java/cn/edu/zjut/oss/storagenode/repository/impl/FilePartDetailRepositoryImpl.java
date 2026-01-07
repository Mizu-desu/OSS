package cn.edu.zjut.oss.storagenode.repository.impl;

import cn.edu.zjut.oss.common.constant.FileConstant;
import cn.edu.zjut.oss.common.mapper.FilePartDetailMapper;
import cn.edu.zjut.oss.common.mapper.DO.FilePartDetail;
import cn.edu.zjut.oss.storagenode.repository.FilePartDetailRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class FilePartDetailRepositoryImpl implements FilePartDetailRepository {
    @Resource
    FilePartDetailMapper filePartDetailMapper;

    @Override
    public Boolean filePartIsExist(String hashInfo) {
        LambdaQueryWrapper<FilePartDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilePartDetail::getHashInfo, hashInfo)
                .eq(FilePartDetail::getIsDeleted, FileConstant.FILE_IS_NOT_DELETED);
        return filePartDetailMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public String uploadPartExistFile(String fileHashInfo, Integer partNumber) {
        LambdaQueryWrapper<FilePartDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilePartDetail::getHashInfo, fileHashInfo)
                .eq(FilePartDetail::getIsDeleted, FileConstant.FILE_IS_NOT_DELETED);
        // 获取原始分片文件索引信息
        FilePartDetail filePartDetail = filePartDetailMapper.selectOne(queryWrapper);
        // 构建新的文件索引信息
        FilePartDetail newFilePartDetail = FilePartDetail.clone(filePartDetail);
        newFilePartDetail.setId(null);
        newFilePartDetail.setPartNumber(partNumber);
        newFilePartDetail.setParentId(null);
        newFilePartDetail.setCreateTime(new Date());
        newFilePartDetail.setUpdateTime(new Date());
        int inserted = filePartDetailMapper.insert(newFilePartDetail);
        if (inserted == 0) {
            log.error("插入新的物理存储文件索引失败:{}", newFilePartDetail);
            return null;
        } else {
            return newFilePartDetail.getId();
        }
    }

    @Override
    public String uploadPartUnExistFile(FilePartDetail filePartDetail) {
        int inserted = filePartDetailMapper.insert(filePartDetail);
        if (inserted == 0) {
            log.error("插入新的物理存储文件失败:{}", filePartDetail);
            return null;
        } else {
            return filePartDetail.getId();
        }
    }


}
