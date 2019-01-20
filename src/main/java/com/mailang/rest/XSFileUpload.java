package com.mailang.rest;

import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import com.mailang.tquery.TemplateDataProvider;
import com.mailang.utils.PROP;
import com.mailang.utils.Utils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;

@Controller
@RequestMapping("rest")
public class XSFileUpload
{
    private static XSLogger LOG = XSLoggerFactory.getLogger(XSFileUpload.class);
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") CommonsMultipartFile file,
                             @RequestParam(value="uploadType") String uploadType)
    {
        String imgName = UUID.randomUUID().toString();
        String postFix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        try
        {

            byte[] imgData = readInputStream(file.getInputStream());
            File imgFile = new File(/*Utils.getAppRootPath() +*/
                PROP.getSYSPropValue("uploadImgPath") +
                imgName +
                postFix);
            FileOutputStream outputStream = new FileOutputStream(imgFile);
            outputStream.write(imgData);
            outputStream.close();
            LOG.debug(imgName + postFix);
            return imgName + postFix;
        }
        catch (Exception e)
        {
            return "";
        }
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        byte[]buffer = new byte[1024];
        int len = 0;
        while ((len=inputStream.read(buffer)) != -1)
        {
            byteArrayInputStream.write(buffer, 0, len);
        }
        inputStream.close();
        return byteArrayInputStream.toByteArray();
    }
}
