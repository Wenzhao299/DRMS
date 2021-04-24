package servlet;

import util.FileCryptoUtil;
import util.GetMacUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("filename");
        System.out.println(fileName);
        String path = this.getServletContext().getRealPath("WEB-INF/file");
        String downloadPath = "E:/DRMS_file";
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
        //File file = new File(path + File.separator + fileName);
        //System.out.println(path + fileName);
        try {
            //FileInputStream inputStream = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header，注意Content-Disposition只支持ASCII，传递中文需要转码
            response.addHeader("Content-Disposition", "attachment;filename =" + new String(fileName.getBytes("gbk"), "iso8859-1"));

            // 创建输出对象
            OutputStream os = response.getOutputStream();
            /*// 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            inputStream.close();*/

            // 文件解密
            String encKey = GetMacUtil.getEncKey(uid);
            try (FileInputStream fis = new FileInputStream(path + File.separator + fileName);
                 /*FileOutputStream fos = new FileOutputStream(downloadPath + File.separator + fileName, true)*/) {
                FileCryptoUtil.decryptedFile(fis, os, encKey);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

