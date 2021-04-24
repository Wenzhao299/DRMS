package servlet;

import service.AccountService;
import service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUidWithAjax")
public class CheckUidWithAjax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        AccountService as = new AccountServiceImpl();
        if(!as.uidExisted(uid)) {
            response.getWriter().print(true);
        }else {
            response.getWriter().print(false);
        }
    }
}
