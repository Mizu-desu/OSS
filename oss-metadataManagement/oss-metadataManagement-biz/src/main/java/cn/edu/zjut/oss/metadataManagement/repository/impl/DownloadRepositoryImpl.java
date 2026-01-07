package cn.edu.zjut.oss.metadataManagement.repository.impl;

import cn.edu.zjut.oss.common.constant.FileConstant;
import cn.edu.zjut.oss.common.mapper.DO.FileDetail;
import cn.edu.zjut.oss.common.mapper.DO.FilePartDetail;
import cn.edu.zjut.oss.common.mapper.FileDetailMapper;
import cn.edu.zjut.oss.common.mapper.FilePartDetailMapper;
import cn.edu.zjut.oss.metadataManagement.repository.DownloadRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DownloadRepositoryImpl implements DownloadRepository {
    @Resource
    private FileDetailMapper fileDetailMapper;

    @Resource
    private FilePartDetailMapper filePartDetailMapper;

    @Override
    public FileDetail findFileDetail(String id) {
        LambdaQueryWrapper<FileDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileDetail::getId, id)
                .eq(FileDetail::getIsDeleted, FileConstant.FILE_IS_NOT_DELETED);
        return fileDetailMapper.selectOne(queryWrapper);
    }

    @Override
    public List<FilePartDetail> findFilePartDetail(String parentId) {
        LambdaQueryWrapper<FilePartDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FilePartDetail::getParentId, parentId)
                .eq(FilePartDetail::getIsDeleted, FileConstant.FILE_IS_NOT_DELETED)
                .orderByAsc(FilePartDetail::getPartNumber);
        return filePartDetailMapper.selectList(queryWrapper);
    }
}
