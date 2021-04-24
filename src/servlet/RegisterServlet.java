package servlet;

import org.apache.commons.lang3.RandomStringUtils;
import service.AccountService;
import service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String pwd = request.getParameter("pwd");
        request.setAttribute("uid", uid);
        request.setAttribute("pwd", pwd);
        AccountService as = new AccountServiceImpl();
        String pwdHash = as.getSHA256(pwd);
        String salt = RandomStringUtils.randomAlphanumeric(20);;
        int counter = 0;
        if(as.register(uid, pwdHash, salt, counter)) {
            //request.getRequestDispatcher("/DRMS/WEB/login.html").forward(request, response);
            response.sendRedirect("/DRMS/WEB/register-success.html");
        }else {
            //request.getRequestDispatcher("/ERESWEB/WEB/user-is-existed.html").forward(request, response);
            response.sendRedirect("/DRMS/WEB/user-is-existed.html");
        }
    }
}
