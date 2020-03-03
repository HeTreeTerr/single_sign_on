package hss.sso.jwt.filter;

import com.alibaba.fastjson.JSON;
import hss.sso.jwt.entity.ReturnEntity;
import hss.sso.jwt.entity.UserContext;
import hss.sso.jwt.service.JwtService;
import hss.sso.jwt.util.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/**", filterName = "loginFilter")
public class LoginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Value("${sso_server}")
//    private String serverHost;

    private JwtService service;

    @Value("${server.servlet.context-path}")
    private String projectName;

    @Autowired
    public LoginFilter(JwtService service) {
        this.service = service;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //请求附件，不拦截
        String requestUrl = httpServletRequest.getRequestURI();
        requestUrl = requestUrl.replaceAll(projectName,"");
        if (requestUrl.endsWith(".css") || requestUrl.endsWith(".js") ||  requestUrl.endsWith(".png")
                || requestUrl.endsWith(".jpg")
                || "/loginPage".equals(requestUrl) || "/login".equals(requestUrl) || "/".equals(requestUrl)
                || "/test/helloPage".equals(requestUrl) || "/inValid".equals(requestUrl) || "/indexPage".equals(requestUrl)) {
            UserContextHolder.remove();
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //String token = httpServletRequest.getParameter("token");
        String token = httpServletRequest.getHeader("Authorization");
        if (this.check(token)) {
            //token和用户id保存到userContextholder
            String str = new String(Base64Utils.decodeFromString(token.split("\\.")[1]));
            UserContext context = new UserContext(JSON.parseObject(str).getString("id"),
                    JSON.parseObject(str).getString("name"), token);
            UserContextHolder.set(context);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(new ReturnEntity(-1, "未登录", null)));

        }
    }

    private boolean check(String jwt) {
        try {
            if (jwt == null || jwt.trim().length() == 0) {
                return false;
            }
            return service.checkJwt(jwt);
        } catch (Exception e) {
            logger.error("认证token失败", e);
            return false;
        }

    }
}

