package servlet;

import entity.FileList;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import service.FileListService;
import service.impl.FileListServiceImpl;
import util.FileCryptoUtil;
import util.GetMacUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Part part = request.getPart("filename");
//        String FileName = UUID.randomUUID() + part.getSubmittedFileName();
//        System.out.println(this.getServletContext().getRealPath("WEB-INF/file/") + FileName);
//        part.write(this.getServletContext().getRealPath("WEB-INF/file/") + FileName);

        // 创建一个DiskFileItemfactory工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建一个ServletFileUpload核心对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        FileListService fs = new FileListServiceImpl();
        boolean flag = false;
        //String mac = request.getParameter("mac");

//        com.jspsmart.upload.SmartUpload su = new com.jspsmart.upload.SmartUpload();
//        su.initialize(this.getServletConfig(), request, response);
//        su.service(request, response);
//        su.setTotalMaxFileSize(100000000);
//        su.setAllowedFilesList("zip,rar");
//        try {
//            su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
//            su.upload();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String mac = su.getRequest().getParameter("mac");
//        System.out.println(mac);

        HttpSession session = request.getSession();
        String uuid = "";
        String filename = "";
        String filesize = "";
        String uptime = null;
        String mac = (String) session.getAttribute("mac");
        String uploader = (String) session.getAttribute("uid");
        // 解析request对象，并得到一个表单项的集合
        try {
            List<FileItem> fileItems = sfu.parseRequest(request);
            // 遍历表单项数据
            for (FileItem fileitem : fileItems) {
                if (fileitem.isFormField()) {
                    //普通表单项
                    String fieldName = fileitem.getFieldName();
                    String fieldValue = fileitem.getString("utf-8");
                    System.out.println(fieldName + ":" + fieldValue);
                } else {
                    //得到文件输入流
                    InputStream is_tmp = fileitem.getInputStream();
                    //创建文件缓存目录
                    String tmpPath = "E:/DRMS_file/tmp";
                    uuid = String.valueOf(UUID.randomUUID());
                    filename = fileitem.getName();
                    if(filename.contains("\\")) {
                        filename = filename.substring(filename.lastIndexOf("\\")+1);
                    }
                    filesize = Integer.toString(Math.toIntExact((fileitem.getSize()/1024))+1);
                    String fileName = uuid + filename;
                    //System.out.println(mac + ":" + fileName);
                    //创建缓存文件路径
                    File tmpPathName = new File(tmpPath + File.separator + fileName);
                    //使用apache commons-io包，将输入流转成缓存文件
                    FileUtils.copyInputStreamToFile(is_tmp, tmpPathName);

                    //文件加密
                    String directoryPath = this.getServletContext().getRealPath("WEB-INF/file");
                    String encKey = GetMacUtil.getEncKey(uploader + mac);
                    try (FileInputStream fis = new FileInputStream(tmpPath + File.separator + fileName);
                         FileOutputStream fos = new FileOutputStream(directoryPath + File.separator + fileName, true)) {
                        FileCryptoUtil.encryptFile(fis, fos, encKey);
                    }

                    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                    uptime = ft.format(new Date());
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag && fs.uploadFile(uuid, filename, filesize, uploader, uptime)) {
            List<FileList> filelist = fs.showFile();
            session.setAttribute("filelist", filelist);
            response.sendRedirect("/DRMS/WEB/index.jsp");
        }else {
            response.sendRedirect("/DRMS/WEB/upload-failed.html");
        }
    }

}
