package mk.ukim.finki.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNorFoundException extends RuntimeException {

    public ManufacturerNorFoundException(Long id) {
        super(String.format("Manufacturer with id %d was not found!", id));
    }
}
