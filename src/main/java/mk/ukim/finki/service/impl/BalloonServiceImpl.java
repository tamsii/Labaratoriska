package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Balloon;
import mk.ukim.finki.model.Manufacturer;
import mk.ukim.finki.model.exception.BalloonNotFoundException;
import mk.ukim.finki.model.exception.ManufacturerNorFoundException;
import mk.ukim.finki.repository.BalloonRepository;
import mk.ukim.finki.repository.ManufacturerRepository;
import mk.ukim.finki.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNorFoundException(manufacturerId));

        return this.balloonRepository.save(name, description, manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        if(!this.balloonRepository.findById(id).isPresent()){
            throw new BalloonNotFoundException(id);
        }
        return this.balloonRepository.findById(id);
    }

}
