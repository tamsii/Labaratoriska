package mk.ukim.finki.web.servlet;

import mk.ukim.finki.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Balloon-List-Servlet", urlPatterns = "")
public class BalloonListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;

    public BalloonListServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloons", balloonService.listAll());

        req.getSession().removeAttribute("order");
        springTemplateEngine.process("listBalloons.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chosenBalloon = req.getParameter("color");

        if (chosenBalloon == null || chosenBalloon.isEmpty()) {
            resp.sendRedirect("/");
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());

        req.getSession().setAttribute("color", chosenBalloon);
        resp.sendRedirect("/selectBalloon");

    }
}

