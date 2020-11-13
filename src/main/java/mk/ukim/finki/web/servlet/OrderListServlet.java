package mk.ukim.finki.web.servlet;

import mk.ukim.finki.model.exception.NoSuchOrderException;
import mk.ukim.finki.model.Order;
import mk.ukim.finki.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Order-List-Servlet", urlPatterns = "/orders")
public class OrderListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public OrderListServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        springTemplateEngine.process("userOrders.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("result");
        String searchName = req.getParameter("searchName");
        String searchAddress = req.getParameter("searchAddress");

        if (searchAddress == null || searchName == null || searchAddress.isEmpty() || searchName.isEmpty()) {
            req.getSession().removeAttribute("result");
            resp.sendRedirect("/userOrders.html");
        }

        Order order = null;
        try {
            order = orderService.placeOrder(searchName, searchAddress);
        }catch (NoSuchOrderException ex){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("error", true);
            context.setVariable("errorMsg", ex.getMessage());
            springTemplateEngine.process("userOrders.html", context, resp.getWriter());
        }
        req.getSession().setAttribute("result", order);
        resp.sendRedirect("/");
    }
}
