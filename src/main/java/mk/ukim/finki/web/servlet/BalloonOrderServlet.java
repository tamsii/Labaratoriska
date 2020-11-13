package mk.ukim.finki.web.servlet;

import mk.ukim.finki.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Balloon-Order-Servlet", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("size", req.getSession().getAttribute("size"));
        context.setVariable("color", req.getSession().getAttribute("color"));


        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");

        if (clientName == null || clientAddress == null || clientAddress.isEmpty() || clientName.isEmpty()) {
            WebContext context = new WebContext(req, resp, req.getServletContext());

            String color = (String)req.getSession().getAttribute("color");
            String size = (String)req.getSession().getAttribute("size");

            context.setVariable("size", size);
            context.setVariable("color", color);


            springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
//            resp.sendRedirect("/balloonOrder.do");
        }

        String color = (String)req.getSession().getAttribute("color");
        String size = (String)req.getSession().getAttribute("size");
        Order order = new Order(color, size, clientName, clientAddress, 1L);
//        req.getSession().removeAttribute("color");
//        req.getSession().removeAttribute("size");
        req.getSession().setAttribute("order", order);

        resp.sendRedirect("/ConfirmationInfo");
    }
}
