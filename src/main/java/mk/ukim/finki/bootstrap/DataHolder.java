package mk.ukim.finki.bootstrap;

import mk.ukim.finki.model.Balloon;
import mk.ukim.finki.model.Manufacturer;
import mk.ukim.finki.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void init(){
        Manufacturer m1 = new Manufacturer("Tamara", "Macedonia", "address_tams");
        Manufacturer m2 = new Manufacturer("Lucy", "Albania", "address_lucy");
        Manufacturer m3 = new Manufacturer("Martin", "Kosovo", "address_martin");

        balloons.add(new Balloon("Red Ballon", "A big Red Balloon",m1));
        balloons.add(new Balloon("Blue Ballon", "A big Blue Balloon",m2));
        balloons.add(new Balloon("Green Ballon", "A big Green Balloon",m2));
        balloons.add(new Balloon("Pink Ballon", "A big Pink Balloon",m1));

        manufacturers.add(m1);
        manufacturers.add(m2);
        manufacturers.add(m3);

    }
}
