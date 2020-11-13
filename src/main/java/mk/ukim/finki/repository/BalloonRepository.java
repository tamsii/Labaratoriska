package mk.ukim.finki.repository;

import mk.ukim.finki.bootstrap.DataHolder;
import mk.ukim.finki.model.Balloon;
import mk.ukim.finki.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloons.stream()
                .filter(b -> b.getName().contains(text) || b.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {
        DataHolder.balloons.removeIf(b -> b.getName().equals(name));
        Balloon balloon = new Balloon(name, description, manufacturer);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id) {
        DataHolder.balloons.removeIf(b -> b.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloons
                .stream()
                .filter( b -> b.getId().equals(id))
                .findFirst();
    }
}
