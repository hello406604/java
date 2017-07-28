package com.kaishengit.crm.controller;

import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.dto.AjaxResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/disk")
public class DiskController extends ParentController {

    @Autowired
    private DiskService diskService;

    /**
     * 网盘首页
     * @param pId
     * @param model
     * @return
     */
    @GetMapping()
    public String diskHome(@RequestParam(value = "_", required = false , defaultValue = "0")Integer pId,
                           Model model){

        List<Disk> diskList = diskService.findDiskByPid(pId);

        if (pId != 0) {
            Disk disk = diskService.findDiskById(pId);
            model.addAttribute("disk",disk);
        }

        model.addAttribute("diskList",diskList);
        return "disk/home";
    }

    /**
     * 新建文件夹
     * @param disk
     * @return
     */
    @PostMapping("/new/folder")
    @ResponseBody
    public AjaxResult newFolder(Disk disk) {
        diskService.newFolder(disk);
        System.out.println(disk.getPid());
        List<Disk> diskList = diskService.findDiskByPid(disk.getPid());
        return AjaxResult.success(diskList);
    }

    /**
     * 文件上传
     * @param file
     * @param pid
     * @param accountId
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam MultipartFile file, Integer pid, Integer accountId) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件大小
        Long fileSize = file.getSize();
        //获取输入流
        InputStream inputStream = file.getInputStream();
        Disk disk = new Disk();
        disk.setPid(pid);
        disk.setName(fileName);
        disk.setAccountId(accountId);
        disk.setFileSize(FileUtils.byteCountToDisplaySize(fileSize));

        diskService.upload(disk, inputStream);

        List<Disk> diskList = diskService.findDiskByPid(disk.getPid());

        return AjaxResult.success(diskList);
    }

    @PostMapping("/rename")
    @ResponseBody
    public AjaxResult rename(Disk disk) {
        diskService.update(disk);
        return AjaxResult.success();
    }

    @GetMapping("/del/{id:\\d+}")
    public String delById(@PathVariable Integer id) {
        Disk disk = diskService.findDiskById(id);
        if (disk == null) {
            throw new NotFoundException();
        }
        diskService.delAllById(id);
        return "redirect:/disk?_=" + disk.getPid();
    }

    @GetMapping("/download")
    public void download (HttpServletResponse response , @RequestParam(name = "_") Integer id) throws IOException {
        Disk disk = diskService.findDiskById(id);
        if (disk == null || disk.getType().equals(Disk.DISK_DIR_NAME)) {
            throw new NotFoundException();
        }
        //设置响应头
        response.setContentType("application/octet-stream");
        //设置弹出下载对话框的文件名
        //处理中文文件名 UTF-8 ---> ISO8859-1
        String fileName = new String (disk.getName().getBytes("UTF-8"),"ISO8859-1");
        response.setHeader("content-Disposition","attachment;filename=\"" + fileName +"\"" );

        //获取响应输出流
        OutputStream outputStream = response.getOutputStream();

        diskService.downloadFile(outputStream,disk);

    }
}
