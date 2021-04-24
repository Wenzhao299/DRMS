package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import service.FileService;
import service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        FileService fs = new FileServiceImpl();
        boolean flag = false;
        String uuid = "";
        String filename = "";
        String filesize = "";
        String uptime = null;
        HttpSession session = request.getSession();
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
                    System.out.println(fieldName + "=====" + fieldValue);
                } else {
                    //上传表单项
                    //得到文件输入流
                    InputStream is = fileitem.getInputStream();
                    //OutputStream fos = FileCryptoUtil.encryptFile((FileInputStream) is, "key");

                    //创建文件存储目录
                    String directoryPath = this.getServletContext().getRealPath("WEB-INF/file");
                    uuid = String.valueOf(UUID.randomUUID());
                    filename = fileitem.getName();
                    filesize = Integer.toString(Math.toIntExact((fileitem.getSize()/1024))+1);
                    String filepath = uuid + filename;
                    //创建文件路径
                    File storeDirectory = new File(directoryPath + File.separator + filepath);
                    //使用apache commons-io包，将输入流转成文件
                    FileUtils.copyInputStreamToFile(is, storeDirectory);
                    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
                    uptime = ft.format(new Date());
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag && fs.uploadFile(uuid, filename, filesize, uploader, uptime)) {
            List<entity.File> filelist = fs.showFile();
            session.setAttribute("filelist", filelist);
            response.sendRedirect("/DRMS/WEB/index.jsp");
        }else {
            response.sendRedirect("/DRMS/WEB/upload-failed.html");
        }
    }

}
