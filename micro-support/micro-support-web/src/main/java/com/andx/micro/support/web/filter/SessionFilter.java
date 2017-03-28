package com.andx.micro.support.web.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 16-11-16.
 */
@Configuration
@WebFilter(displayName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession(true);
        chain.doFilter(req, response);

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse rsp = (HttpServletResponse) response;
//        String uri = req.getRequestURI();
//        String [] notFilterRegexs = new String[] {"^/views/user/login.html", "^/views/common/\\w+", "^/user/login", "^/resources/\\w+"};
//        boolean isFilter = true;
//        for (String notFilterRegex : notFilterRegexs) {
//            Pattern pattern = Pattern.compile(notFilterRegex);
//            Matcher matcher = pattern.matcher(req.getRequestURI());
//            if (matcher.find()) {
//                isFilter = false;
//                break;
//            }
//        }
//        if (isFilter) {
//            if (req.getSession().getAttribute("userId") == null) {
//                rsp.sendRedirect("/views/user/login.html");
//            } else {
//                chain.doFilter(req, rsp);
//            }
//        } else {
//            chain.doFilter(req, rsp);

//        }
    }

    @Override
    public void destroy() {

    }
}
