package servlet;

import entity.File;
import service.AccountService;
import service.FileService;
import service.impl.AccountServiceImpl;
import service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String pwd = request.getParameter("pwd");
        HttpSession session = request.getSession();
        session.setAttribute("uid", uid);
        session.setAttribute("pwd", pwd);
        AccountService as = new AccountServiceImpl();
        FileService fs = new FileServiceImpl();
        String pwdHash = as.getSHA256(pwd);
        String salt = as.getSalt(uid);
        int counter = as.getCounter(uid);
        //System.out.println("pwdHash:" + pwdHash + "\nsalt:" + salt+ "\ncounter:" + counter);
        if(as.login(uid, pwdHash, salt, counter+1) && as.updateCounter(uid)) {
            List<File> filelist = fs.showFile();
            session.setAttribute("filelist", filelist);
            //request.getRequestDispatcher("/DRMS/WEB/index.jsp").forward(request, response);
            response.sendRedirect("/DRMS/WEB/index.jsp");
        }else {
            //request.getRequestDispatcher("/ERES/WEB/login-failed.html").forward(request, response);
            response.sendRedirect("/DRMS/WEB/login-failed.html");
        }
    }
}
