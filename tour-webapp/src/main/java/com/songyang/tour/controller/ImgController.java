package com.songyang.tour.controller;

import com.alibaba.fastjson.JSONObject;
import com.songyang.tour.components.ImgComponent;
import com.songyang.tour.constants.VmConstans;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

//import com.tongbanjie.commons.lang.Result;


/**
 * 图片管理
 */
@Controller
@RequestMapping("/img")
public class ImgController {

    private static final Logger LOG = LoggerFactory.getLogger(ImgController.class);

    @Resource
    private ImgComponent imgComponent;


    @RequestMapping("/init")
    public String init(HttpServletRequest request, Model model) {

        return VmConstans.IMG_EDIT;
    }


    @RequestMapping("/evl")
    public String evl(HttpServletRequest request, Model model) {

        return VmConstans.EVL_EDIT;
    }

    @RequestMapping("/upload")
    @ResponseBody
//    public JSONObject upload(@RequestParam(value = "imgFile", required = false) CommonsMultipartFile imgFile,
//                             @RequestParam(value = "module", required = false) String module, Model model) {
    public JSONObject upload(@RequestParam(value = "imgFile", required = false) MultipartFile  imgFile,
                             @RequestParam(value = "module", required = false) String module, Model model) {


        JSONObject data = new JSONObject();

        try {
//            // 转成file
//            File file = multipartToFile(imgFile);
//
//            // 文件名后缀
//            String imageType = "";
//            String contentType = imgFile.getContentType();
//            if (contentType.indexOf("image/") > -1) {
//                imageType = contentType.substring(imgFile.getContentType().indexOf("image/") + 6);
//            } else {
//                imageType = getExt(imgFile.getOriginalFilename());
//            }
//
//            if (!isValidImgType(imageType)) {
//                data.put("code", 0);
//                data.put("msg", "图片格式不正确");
//            }
//
//            String moduleDir = module;
//            if (StringUtils.isBlank(module)) {
//                moduleDir = "other";
//            }
//
//            // 生成图片文件名
//            String originalFileName = imgFile.getOriginalFilename(); //获取文件名;
//            //文件名
//            String fileName = format(new Date(), yyyyMMddHHmmss) + '_' + originalFileName;
//            //动态目录
//            String dynamicDir = "/"+moduleDir + "/" + format(new Date(), yyyyMMdd) + "/";
//            //远程目录
//            String remotePicPath = TourConstants.remoteFilePath + dynamicDir;
//
//            File f1 = new File(remotePicPath);
//            if (!f1.exists()) {
//                f1.mkdir();// 创建图片文件夹
//            }
//            // 上传文件
//            uploadFile(remotePicPath, fileName, file);

            CommonsMultipartFile temp = (CommonsMultipartFile)imgFile;
            return imgComponent.save(temp,module);


        } catch (Throwable e) {
            LOG.error("upload img exception", e);
            data.put("code", 0);
            data.put("msg", e.getMessage());
        }

        return data;


//		String fileName = imgFile.getOriginalFilename(); //获取文件名
//		// 图片格式校验
//		String imageType = "";
//		String contentType = imgFile.getContentType();
//		if (contentType.indexOf("image/") > -1) {
//			imageType = contentType.substring(imgFile.getContentType().indexOf("image/") + 6);
//		} else {
//			imageType = getExt(imgFile.getOriginalFilename());
//		}
//		if (!isValidImgType(imageType)) {
//			// 图片格式不正确
////			return com.tongbanjie.commons.api.model.Model.buidBusinessFail("图片格式不正确");
//		} else {
////			String fileName = KeyGenerateUtil.generateIdKey() + "." + imageType;
//
//			Result<String> result = null;
//			try {
//
//				BufferedImage image = ImageIO.read(imgFile.getInputStream());
//				double width = image.getWidth();
//				double height = image.getHeight();
//				double w = width;
////				if (width > ConfigLoader.getSnsPicMaxWidthSize()) {
////					w = ConfigLoader.getSnsPicMaxWidthSize();
//				if (width > 800) {
//					w = 800;
//					double rate = w / width;
//					height = height * rate;
//
//				}
//				ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(1024 * 1024 * 10);
//				InputStream inputStream = imgFile.getInputStream();
//						File file = multipartToFile(imgFile);
//				Thumbnails.of(inputStream).size((int) w, (int) height).imageType(BufferedImage.TYPE_INT_ARGB).toFile(file);
//				uploadFile("D:/Test/", "1245.png",file);
////				Thumbnails.of(inputStream).size((int) w, (int) height).imageType(BufferedImage.TYPE_INT_ARGB).toOutputStream(byteOutputStream);
////				byte[] bytes = byteOutputStream.toByteArray();
////				result = upload.uploadImage(fileName, bytes);
////				byteOutputStream.close();
////				inputStream.close();
//			} catch (IOException e) {
////				logger.warn("压缩图片上传出现异常", e);
////				return com.tongbanjie.commons.api.model.Model.buidBusinessFail("上传失败,请重试");
//			}

//		}

    }


    /**
     * 展示图片到页面
     *
     * @param img
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "showPic", method = {RequestMethod.GET})
    public String showPic(@RequestParam(value = "imgPath", required = true) String img, HttpServletResponse response) {

        FileInputStream fis = null;
        OutputStream os = null;
        try {

            if(StringUtils.isBlank(img)){
                LOG.error("ImgController#showPic_imgPath_is_blank>>>");
                return "ok";
            }

            //String dirDatePath = StringUtils.substring(imgBytes.trim(), 0, 8);
            //String remotePicPath = TourConstants.remoteFilePath + dirDatePath + "/" + imgBytes.trim();
            //response.setContentType("image/*");
            fis = new FileInputStream(img); // 以byte流的方式打开文件
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            LOG.error("ImgController#showPic_is_exception>>>>", e);
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            LOG.error("ImgController#showPic_is_IOException>>>>", e);
        }
        return "ok";
    }


//    /**
//     * 前缀+5位随机数
//     *
//     * @param prefix
//     * @return
//     */
//    public static String buildId(String prefix) {
//        return prefix + RandomStringUtils.randomNumeric(5);
//    }



}
