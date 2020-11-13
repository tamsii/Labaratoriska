package mk.ukim.finki.service;

import mk.ukim.finki.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {

    List<Balloon> listAll();

    List<Balloon> searchByNameOrDescription(String text);

    Optional<Balloon> save(String name, String description, Long manufacturerId);

    void deleteById(Long id);

    Optional<Balloon> findById(Long id);
}
