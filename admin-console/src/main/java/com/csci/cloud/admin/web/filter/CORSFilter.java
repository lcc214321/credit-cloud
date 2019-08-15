package com.csci.cloud.admin.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Created by ben on 2018/10/8. benkris1@126.com
 */
@WebFilter(urlPatterns = "/api/*")
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

  public static Pattern pattern = Pattern
      .compile("http(s)?://([\\w-]+\\.)+chinacsci\\.com(\\:[\\d]+)?(/[\\w- ./?%&=]*)?");


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    //response.setHeader("Access-Control-Allow-Origin", "*");
    String origin = ((HttpServletRequest) request).getHeader("Origin");

    if (null != origin && pattern.matcher(origin).find()) {
      response.setHeader("Access-Control-Allow-Origin", origin);
    }
    response.setHeader("Vary", "Origin");
    response.setHeader("Access-Control-Allow-Credentials", "false");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers",
        "X-Requested-With,Access-Control-Allow-Credentials, Content-Type,Cache-Control, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }


  public static void main(String[] args) {

    System.out.println(pattern.matcher("https://pass.chinacsci.com:5050").matches());
  }
}
