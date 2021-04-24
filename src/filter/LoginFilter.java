package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
        Object obj = request.getSession().getAttribute("uid");
        //System.out.println(url+obj);
        if(url.equals("/login.html") || url.equals("/LoginServlet") || url.equals("/register.html") || url.equals("/RegisterServlet") || url.equals("/CheckUidWithAjax") || url.equals("/UploadFile") || url.equals("/DownloadFile")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            if(obj==null) {
                response.sendRedirect("/DRMS/WEB/login.html");
            }else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}
