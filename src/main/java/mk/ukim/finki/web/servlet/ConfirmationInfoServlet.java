package mk.ukim.finki.web.servlet;

import mk.ukim.finki.bootstrap.DataHolder;
import mk.ukim.finki.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Confirmation-Info-Servlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;


    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("clientAgent", req.getHeader("User-Agent"));
        context.setVariable("ipAddress", req.getRemoteAddr());
        Order order = (Order)req.getSession().getAttribute("order");
        context.setVariable("order", order);
        DataHolder.orders.add(order);

        context.getSession().invalidate();

        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());

    }
}
