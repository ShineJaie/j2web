package com.j2web.web.controller.login;

import com.j2web.web.utils.MyWebUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录控制
 * Created by wxj on 16-7-12.
 */
@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    /**
     * 登录接口, 判断会话过期、密码并保存当前用户浏览器在前一次登录的页面
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        String status = MyWebUtils.dealWithNullVal(request.getParameter("status"));

        ModelAndView mav = new ModelAndView();

        if (status.equals("invalid")) {
            mav.addObject("error", "会话已经过期!");
            logger.info("会话已经过期");
            response.setStatus(499); // 登录过期的状态码(自定义, 避免和已经存在的 http 状态码相同)

        } else if (status.equals("error")) {
            mav.addObject("error", "用户名或密码错误!");
            logger.info("用户名或密码错误");

            // 更新登录, 如果登录错误再次获取登录后的目标地址
            String targetUrl = getRememberMeTargetUrlFromSession(request);
            if (StringUtils.hasText(targetUrl)) {
                mav.addObject("loginUpdate", true);
            }
        }

        if (status.equals("logout")) {
            mav.addObject("msg", "注销成功!");
        }
        mav.setViewName("login");

        return mav;
    }

    /**
     * 注销登录
     *
     * @param request  httpRequest
     * @param response httpResponse
     * @return 首页
     */
    @RequestMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        SecurityContextHolder.getContext().setAuthentication(null);

        return "redirect:/login?logout";
    }

    /**
     * 登录后的首页
     *
     * @return page
     */
    @RequestMapping(value = {"/", "/home**"})
    public ModelAndView defaultPage(HttpServletRequest request) {

        // 登录后的目标地址
        String targetUrl = getRememberMeTargetUrlFromSession(request);
        if (StringUtils.hasText(targetUrl)) {

            deleteRememberMeTargetUrlFromSession(request);
            return new ModelAndView("redirect:" + targetUrl);
        }

        return new ModelAndView("home");
    }

    /**
     * 更新用户名密码页面(此处只当测试).<br/>
     * 如果用户是通过 cookie 登录的, 则转到登录页面, 再次输入密码.<br/>
     * 为了避免被盗请记住我cookie来更新信息
     *
     * @return page
     */
    @RequestMapping(value = "/admin**")
    public ModelAndView adminPage(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Spring Security 表单登录 - 能过数据库身份认证");
        mav.addObject("message", "此页面只有管理员才可访问!");

        if (isRememberMeAuthenticated()) {
            // 更新登录
            setRememberMeTargetUrlToSession(request, "/admin");
            mav.addObject("loginUpdate", true);
            mav.setViewName("/login");

        } else {
            mav.setViewName("admin");
        }

        return mav;
    }

    /**
     * for 403 access denied page
     *
     * @return 403 页面
     */
    @RequestMapping(value = "/403")
    public ModelAndView accesssDenied() {

        ModelAndView mav = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            mav.addObject("username", userDetail.getUsername());
        }

        mav.setViewName("/error/403");
        return mav;
    }

    /**
     * Check if user is login by remember me cookie, refer
     * org.springframework.security.authentication.AuthenticationTrustResolverImpl
     */
    private boolean isRememberMeAuthenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * 设置登录后的目标地址
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request, String targetUrl) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("targetUrl", targetUrl);
        }
    }

    /**
     * 从 session 中获取登录成功后的目标地址
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if (session != null) {
            targetUrl = session.getAttribute("targetUrl") == null ? ""
                    : session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }

    /**
     * 从 session 中删除获取登录成功后的目标地址
     */
    private void deleteRememberMeTargetUrlFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("targetUrl", "");
        }
    }

}
