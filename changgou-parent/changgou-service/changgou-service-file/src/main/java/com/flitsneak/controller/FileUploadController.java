package com.flitsneak.controller;

import com.flitsneak.file.FastDFSFile;
import com.flitsneak.util.FastDFSUtil;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {
    /***
     * 文件上传
     * @return
     */
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        //封装一个FastDFSFile
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(), //文件名字
                file.getBytes(),            //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名

        //文件上传
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        //组装文件上传地址
        return FastDFSUtil.getTrackerUrl()+"/"+uploads[0]+"/"+uploads[1];
    }
    //这里接收不到前端file，应该是接收参数问题，自己测试没有任何问题
//    public static void main(String[] args) {
//        try {
//            File pdfFile = new File("D:\\应用宝照片备份\\IMG_20180807_124729.jpg");
//            FileInputStream fileInputStream = new FileInputStream(pdfFile);
//            MultipartFile file = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
//                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//            //封装一个FastDFSFile
//            FastDFSFile fastDFSFile = new FastDFSFile(
//                    file.getOriginalFilename(), //文件名字
//                    file.getBytes(),            //文件字节数组
//                    StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名
//
//            //文件上传
//            String[] uploads = FastDFSUtil.upload(fastDFSFile);
//            System.out.println(uploads);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

