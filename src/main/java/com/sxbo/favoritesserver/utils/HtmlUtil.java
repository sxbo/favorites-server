package com.sxbo.favoritesserver.utils;

import com.sxbo.favoritesserver.comm.R;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @Author xiaobo GG [https://github.com/sxbo]
 * @Date 2017/10/2010:06
 */
public class HtmlUtil {
    private static Logger logger = Logger.getLogger(HtmlUtil.class);

    public static String getImge(String url){
        String logo = "";
        logo = getPageImg(url);
        if (StringUtils.isBlank(logo)){
            logo = R.default_logo;
        }

        return logo;
    }

    public static String getPageImg(String url){
        String imgUrl="";
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent(R.userAgent).get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for(Element image : images){
                imgUrl=image.attr("src");
                if(StringUtils.isNotBlank(imgUrl) ){
                    if(imgUrl.startsWith("//")){
                        imgUrl = "http:" + imgUrl;
                    }else if(!imgUrl.startsWith("http") && !imgUrl.startsWith("/")){
                        imgUrl=URLUtil.getDomainUrl(url) + "/" + imgUrl;
                    }else if(!imgUrl.startsWith("http")){
                        imgUrl=URLUtil.getDomainUrl(url)+imgUrl;
                    }
                }
                // 判断图片大小
                String fileUrl = download(imgUrl);
                if (fileUrl == null){
                    return  imgUrl;
                }
                File picture = new File(fileUrl);
                FileInputStream in = new FileInputStream(picture);
                BufferedImage sourceImg = ImageIO.read(in);
                String weight = String.format("%.1f",picture.length()/1024.0);
                int width = sourceImg.getWidth();
                int height = sourceImg.getHeight();
                // 删除临时文件
                if(picture.exists()){
                    in.close();
                    picture.delete();
                }
                if(Double.parseDouble(weight) <= 0 || width <=0 || height <= 0){
                    logger.info("当前图片大小为0，继续获取图片链接");
                    imgUrl="";
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("getPageImg失败,url:"+url,e);
        }
        return imgUrl;
    }


    private static String download(String url){
        try {
            String imageName = url.substring(url.lastIndexOf("/") + 1,
                    url.length());

            URL uri = new URL(url);
            InputStream in = uri.openStream();
            String dirName = "static/temp/";
            File dirFile = new File(dirName);
            if(!dirFile.isDirectory()){
                dirFile.mkdir();
            }
            String fileName = dirName+imageName;
            File file = new File(dirFile,imageName);
            FileOutputStream fo = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf, 0, buf.length)) != -1) {
                fo.write(buf, 0, length);
            }
            in.close();
            fo.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
