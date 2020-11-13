package mk.ukim.finki.repository;

import mk.ukim.finki.bootstrap.DataHolder;
import mk.ukim.finki.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturers.stream()
                .filter(m -> m.getId().equals(id)).findFirst();
    }
}
