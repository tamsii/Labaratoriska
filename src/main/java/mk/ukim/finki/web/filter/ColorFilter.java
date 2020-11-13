//package mk.ukim.finki.web.filter;
//
//import mk.ukim.finki.model.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class ColorFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String color = (String) request.getSession().getAttribute("color");
//        Order order = (Order) request.getSession().getAttribute("order");
//        String path = request.getServletPath();
//
//        if (path.equals("/bootstrap/*")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//        if (path.equals("/") || (path.equals("/balloonOrder.do") && color == null) || (path.equals("/ConfirmationInfo") && order == null)) {
//            response.sendRedirect("/balloons");
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
