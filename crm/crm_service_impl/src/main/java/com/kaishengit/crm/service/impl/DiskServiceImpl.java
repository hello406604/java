package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.entity.DiskExample;
import com.kaishengit.crm.mapper.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.exception.ServiceException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskMapper diskMapper;

    @Value("${upload.path}")
    private String path;
    @Override
    public List<Disk> findDiskByPid(Integer pId) {
        DiskExample  d = new DiskExample();
        DiskExample.Criteria criteria = d.createCriteria().andPidEqualTo(pId);
        return diskMapper.selectByExample(d);
    }

    @Override
    public Disk findDiskById(Integer id) {
        return diskMapper.selectByPrimaryKey(id);
    }

    @Override
    public void newFolder(Disk disk) {
        disk.setUpdateTime(new Date());
        disk.setType(Disk.DISK_DIR_NAME);
        diskMapper.insert(disk);
    }

    @Override
    @Transactional
    public void upload(Disk disk, InputStream inputStream) {
        String fileName = disk.getName();
        disk.setSaveName(UUID.randomUUID() + fileName.substring(fileName.lastIndexOf(".")));
        disk.setUpdateTime(new Date());
        disk.setDownloadCount(0);
        disk.setType(Disk.DISK_FILE_NAME);
        diskMapper.insert(disk);

        try {
            OutputStream outputStream = new FileOutputStream(new File(path,disk.getSaveName()));
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            throw new ServiceException("文件上传异常");
        }

    }

    @Override
    public void update(Disk disk) {
        disk.setUpdateTime(new Date());
        diskMapper.updateByPrimaryKeySelective(disk);
    }

    @Override
    public void downloadFile(OutputStream outputStream, Disk disk) {
        //更新文件下载数量
        disk.setDownloadCount(disk.getDownloadCount() + 1);
        diskMapper.updateByPrimaryKey(disk);
        try {
            File file = new File(path,disk.getSaveName());
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                IOUtils.copy(inputStream,outputStream);
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } else {
                throw new ServiceException("下载的资源不存在或已被删除");
            }
        } catch (IOException e) {
            throw new ServiceException("下载文件异常",e);
        }
    }

    @Override
    public void delAllById(Integer id) {
        Disk disk = diskMapper.selectByPrimaryKey(id);
        if(disk.getType().equals("file")){
            diskMapper.deleteByPrimaryKey(id);
        } else  {
            delAllByPId(id);
        }
    }

    private void delAllByPId(Integer id) {
        DiskExample diskExample = new DiskExample();
        diskExample.createCriteria().andPidEqualTo(id);
        List<Disk> diskList = diskMapper.selectByExample(diskExample);
        if (diskList.size() != 0) {
            for(Disk disk : diskList) {
                if (disk.getType().equals("file")) {
                    diskMapper.deleteByPrimaryKey(disk.getId());
                } else  {
                    delAllByPId(disk.getId());
                }
            }
        }
        diskMapper.deleteByPrimaryKey(id);
    }

}
