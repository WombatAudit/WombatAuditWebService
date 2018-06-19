package com.wombat.blw.Util;

import com.wombat.blw.Exception.FileIOException;
import com.wombat.blw.Exception.FileTypeException;
import com.wombat.blw.Exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static final String UPLOAD_DIRECTORY = "/srv/ftp/www/images/";

    public static final String TEST_DIRECTORY = "D:\\";

    public static final String[] IMAGE_TYPES = {"png", "jpg", "gif"};

    public static String fileUpload(MultipartFile file, String uploadDir, String[] types) {
        if (file == null) {
            throw new NotFoundException();
        }
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = file.getOriginalFilename();
            String fileType = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (fileType != null) {
                for (String str : types) {
                    if (str.toUpperCase().equals(fileType.toUpperCase())) {
                        fileName = "wa_" + String.valueOf(System.currentTimeMillis()) + "." + fileType;
                        file.transferTo(new File(uploadDir + fileName));
                        //Runtime.getRuntime().exec("chmod 644 " + uploadDir + fileName);
                        return "http://47.100.253.251:8081/images/" + fileName;
                    }
                }
            }
            throw new FileTypeException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileIOException();
    }

    public static String imageUpload(MultipartFile file) {
        return fileUpload(file, UPLOAD_DIRECTORY, IMAGE_TYPES);
//        return imageUploadTest(file);
    }

    public static String imageUploadTest(MultipartFile file) {
        return fileUpload(file, TEST_DIRECTORY, IMAGE_TYPES);
    }
}
