package com.xuyh.servlet;

import com.xuyh.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: XUYH
 * @Description:
 * @Date: 2018/12/17
 * @Version:
 */
@WebServlet(urlPatterns="/jdbcServlet", description="Servlet的说明",initParams= {@WebInitParam(name="jdbc",value="jdbc")})
public class JdbcServlet extends HttpServlet{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcService jdbcService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service(req, resp);
    }

    @Override
    @Transactional
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List resultList = jdbcService.getUsers4Jdbc(jdbcTemplate);

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");
        out.println("<title>Hello World</title>");
        out.println("</head>");
        out.println("<body>");
        for(int i=0; i<resultList.size(); i++){
            out.println(resultList.get(i).toString());
        }
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }
}
