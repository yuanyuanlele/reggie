package com.le.reggie.controller;

import com.le.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());

        //原始文件名
        String originalFileName= file.getOriginalFilename();
        String suffix =originalFileName.substring(originalFileName.lastIndexOf("."));
        //UUid文件名
        String filename= UUID.randomUUID().toString()+suffix;

        //创建一个目录
        File dir=new File(basePath);
        if (!dir.exists()){
            //创建目录
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(filename);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream servletOutputStream= response.getOutputStream();

            response.setContentType("image/jpeg");

            int len=0;
            byte[] bytes=new byte[1024];

            while ((len=fileInputStream.read(bytes))!=-1){
                servletOutputStream.write(bytes,0,len);
                servletOutputStream.flush();
            }
            //关闭资源
            servletOutputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
