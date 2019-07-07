package com.java.util;

import com.java.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImgUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    public static String generateThumbnail(ImageHolder imageHolder, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(imageHolder.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(imageHolder.getImage()).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/logo.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PathUtil.getImgBasePath() + relativeAddr;
    }

    /*创建目标路径所涉及的目录*/
    public static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /*获取输入文件的扩展名*/
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /*生成一个年月日时分秒加上五个随机数的文件名*/
    public static String getRandomFileName() {
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /*删除图片文件或者删除文件夹以其中的文件*/
    public static void deleteFileOrDir(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            if(file.isDirectory()){
                File[] fileList = file.listFiles();
                for(int i = 0; i < fileList.length; i++){
                    fileList[i].delete();
                }
            }
            file.delete();
        }
    }


    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("E:\\图片\\tupian.jpg"))
                .size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT,
                        ImageIO.read(new File(basePath + "/logo.png")), 0.25f)
                .outputQuality(0.8f)
                .toFile(new File("E:\\图片\\tupian_new.jpg"));
    }
}
