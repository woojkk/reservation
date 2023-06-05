package com.woojkk.reservation.config.filter;

import com.woojkk.reservation.service.ManagerService;
import domain.common.UserVo;
import domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/manager/*")
@RequiredArgsConstructor
public class ManagerFilter implements Filter {

    private final JwtAuthenticationProvider provider;
    private final ManagerService managerService;


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");

        if (!provider.validateToken(token)) {
            throw new ServletException("Invalid Access");
        }

        UserVo vo = provider.getUserVo(token);
        managerService.findByIdAndEmail(vo.getGetId(), vo.getGetEmail())
                .orElseThrow(() -> new ServletException("Invalid Access"));
        chain.doFilter(request,response);
    }
}
