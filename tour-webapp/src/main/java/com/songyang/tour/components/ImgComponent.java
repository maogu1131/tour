package com.songyang.tour.components;/**
 * Created by lenovo on 2017/11/1.
 */

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.constants.TourConstants;
import com.songyang.tour.pojo.SyEvaluate;
import com.songyang.tour.service.SyEvaluateService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片组件
 *
 * @author
 * @create 2017-11-01 17:29
 **/
@Component
public class ImgComponent {

    private static final Logger LOG = LoggerFactory.getLogger(ImgComponent.class);

    public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public final static String yyyyMMdd = "yyyyMMdd";

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public JSONObject save(CommonsMultipartFile imgFile, String module) throws Throwable {

        JSONObject data = new JSONObject();

        // 转成file
        File file = multipartToFile(imgFile);

        // 文件名后缀
        String imageType = "";
        String contentType = imgFile.getContentType();
        if (contentType.indexOf("image/") > -1) {
            imageType = contentType.substring(imgFile.getContentType().indexOf("image/") + 6);
        } else {
            imageType = getExt(imgFile.getOriginalFilename());
        }

        if (!isValidImgType(imageType)) {
            data.put("code", 0);
            data.put("msg", "图片格式不正确");
            return data;
        }

        String moduleDir = module;
        if (StringUtils.isBlank(module)) {
            moduleDir = "other";
        }

        // 生成图片文件名
        String originalFileName = imgFile.getOriginalFilename(); //获取文件名;
        //文件名
        String fileName = format(new Date(), yyyyMMddHHmmss) + '_' + originalFileName;
        //动态目录
        String dynamicDir = "/"+moduleDir + "/" + format(new Date(), yyyyMMdd) + "/";
        //远程目录
        String remotePicPath = TourConstants.remoteFilePath + dynamicDir;

        File f1 = new File(remotePicPath);
        if (!f1.exists()) {
            f1.mkdir();// 创建图片文件夹
        }
        // 上传文件
        uploadFile(remotePicPath, fileName, file);

        data.put("code", 1);
        data.put("url", dynamicDir + fileName);
        return data;

    }

    /**
     * 图片文件转成普通file
     *
     * @param multfile
     * @return
     * @throws IOException
     */
    private File multipartToFile(MultipartFile multfile) throws IOException {
        CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
        //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        //手动创建临时文件
//		if(file.length() < CommonConstants.MIN_FILE_SIZE){  // 太大不允许
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                file.getName());
        multfile.transferTo(tmpFile);
        return tmpFile;
//		}
//		return file;
    }

    /**
     * 按格式将日期转成string
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }



    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    private String getExt(String fileName) {
        String ext = "";
        if (StringUtils.isBlank(fileName)) {
            return ext;
        }
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            try {
                ext = fileName.substring(index + 1);
            } catch (Exception e) {
                ext = "";
            }
        }
        return ext;
    }

    // 判断文件是否是图片
    private static boolean isValidImgType(String type) {
        if (StringUtils.isBlank(type)) {
            return false;
        }
        type = type.toLowerCase();
        if ("jpg".equals(type)
                || "bmp".equals(type)
                || "jpeg".equals(type)
                || "png".equals(type)) {
            return true;
        }
        return false;
    }


    /**
     * 上传文件
     *
     * @param targetDirectory 文件保存路径
     * @param targetFileName  文件保存名
     * @param file            文件
     * @author 渣渣
     */
    private static void uploadFile(String targetDirectory, String targetFileName, File file) {
        try {
            File target = new File(targetDirectory, targetFileName);
            /**
             * 上传整个文件夹文件
             * */
            //FileUtils.copyDirectory(file,target);
            /**
             * 上传单个文件
             */
            FileUtils.copyFile(file, target);
        } catch (IOException e) {
            LOG.error("uploadFile is exception", e);
        }
    }


}
