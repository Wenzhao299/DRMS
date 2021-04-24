package servlet;

import entity.FileList;
import service.FileListService;
import service.impl.FileListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String filename = request.getParameter("filename");
        String filepath = this.getServletContext().getRealPath("WEB-INF/file") + File.separator + uuid + filename;
        FileListService fs = new FileListServiceImpl();
        HttpSession session = request.getSession();
        if(fs.deleteFile(uuid, filename) && deleteFile(filepath)) {
            List<FileList> filelist = fs.showFile();
            session.setAttribute("filelist", filelist);
            response.sendRedirect("/DRMS/WEB/index.jsp");
        }else {

        }
    }

    protected boolean deleteFile(String filepath) {
        boolean flag = false;
        try{
            File file = new File(filepath);
            if(file.delete()){
                flag = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
