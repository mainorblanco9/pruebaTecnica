package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cliente no encontrado-")
public class customerNotFoundExe extends Exception{

    private static final long serialVersionUID = 1L;

    public customerNotFoundExe(String message){

        super(message);
    }
}
