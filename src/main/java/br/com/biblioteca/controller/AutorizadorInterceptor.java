package br.com.biblioteca.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response,
            Object controller) throws Exception {

        String uri = request.getRequestURI();
        if( uri.endsWith("login")   || 
            uri.endsWith("/") || 
            uri.endsWith("cadastrar") ||
            uri.endsWith("salvar-externo") ||
            uri.contains("resources")) {
            return true;
        }

        if(request.getSession().getAttribute("usuarioLogado") != null) {
            return true;
        }

        response.sendRedirect("/biblioteca");
        return false;
    }
}