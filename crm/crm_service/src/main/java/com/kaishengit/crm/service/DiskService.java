package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Disk;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface DiskService  {
    List<Disk> findDiskByPid(Integer pId);

    Disk findDiskById(Integer id);

    void newFolder(Disk disk);

    void upload(Disk disk, InputStream inputStream);

    void update(Disk disk);

    void delAllById(Integer id);

    void downloadFile(OutputStream outputStream, Disk disk);
}
