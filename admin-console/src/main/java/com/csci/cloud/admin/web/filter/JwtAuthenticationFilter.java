package com.csci.cloud.admin.web.filter;

import com.csci.cloud.admin.auth.UserTokenFactory;
import com.csci.cloud.admin.constant.AuthConstant;
import com.csci.cloud.admin.data.vo.ResponseVo;
import com.csci.cloud.admin.exception.AuthenticationException;
import com.csci.cloud.admin.utils.ErrorCode;
import com.csci.cloud.admin.utils.JsonUtils;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by ben on 2018/9/17. benkris1@126.com
 */
@WebFilter(urlPatterns = "/api/*")
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private static final PathMatcher pathMatcher = new AntPathMatcher();

  @Autowired
  private UserTokenFactory userTokenFactory;

  private List<String> excludes = Lists.newArrayList("/api/users/login",
      "/api/users/register",
      "/api/health/*");

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    logger.info("filter request");

    if(!"OPTIONS".equals(request.getMethod()) && !excludePath(request)) {
      try {
        String token = request.getHeader(AuthConstant.HEADER_AUTHORIZATION);
        if(StringUtils.isBlank(token)) {
          throw new AuthenticationException(ErrorCode.AUTH_ERROR);
        }
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
        String userId = userTokenFactory.parseAccessToken(token);
        request.setAttribute("userId",userId);
      }catch (AuthenticationException e) {
        log.info(ExceptionUtils.getMessage(e));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.getWriter().write(JsonUtils.toJsonString(ResponseVo.error(e.getReason(),e.getErrorCode(),null)));
        return;
      }
    }
    //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
    filterChain.doFilter(request, response);
  }

  private boolean excludePath(HttpServletRequest request) {
    String path = request.getRequestURI()
        .substring(request.getContextPath().length()).replaceAll("[/]+$", "");
    return excludes.stream().anyMatch(exclude -> pathMatcher.match(exclude, path));
  }
}
