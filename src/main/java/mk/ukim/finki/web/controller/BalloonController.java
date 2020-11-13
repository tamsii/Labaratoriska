package mk.ukim.finki.web.controller;

import mk.ukim.finki.model.Balloon;
import mk.ukim.finki.model.Manufacturer;
import mk.ukim.finki.model.Order;
import mk.ukim.finki.model.exception.BalloonNotFoundException;
import mk.ukim.finki.service.BalloonService;
import mk.ukim.finki.service.ManufacturerService;
import mk.ukim.finki.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("balloons", this.balloonService.listAll());
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,@RequestParam String description,@RequestParam Long manufacturer){
        this.balloonService.save(name, description, manufacturer);
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        Balloon balloon = null;
        try {
            balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();

            model.addAttribute("balloon", balloon);
            model.addAttribute("manufacturers", manufacturers);

            return "add-balloon";
        } catch (BalloonNotFoundException exception) {

            return "redirect:/balloons?error=" +exception.getMessage();
        }
    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        List<Order> orders = this.orderService.findAll();
        model.addAttribute("orders", orders);
        return "userOrders";
    }

    @GetMapping("/select")
    public String emptySelect(Model model){
        return "redirect:/balloons?error=No+balloon+selected";

    }

    @PostMapping("/select")
    public String selectBalloon(@RequestParam(required = false) String color, Model model, HttpServletRequest request){
        if (color == null) {
            return "redirect:/balloons";
        }
        System.out.println(color);
        model.addAttribute("color", color);
        request.getSession().setAttribute("color", color);
        return "selectBalloonSize";
    }

}
