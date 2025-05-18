package pl.edu.vistula.firstrestapistring.product.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.edu.vistula.firstrestapistring.product.shared.api.response.ErrorMessageResponse;
import pl.edu.vistula.firstrestapistring.product.support.exception.ProductNotFoundException;


@ControllerAdvice
public class ProductExceptionAdvisor {

    private static final Logger LOG = LoggerFactory.getLogger(ProductExceptionAdvisor.class);

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse productNotFound(ProductNotFoundException e) {
        LOG.error("Product not found: {}", e.getMessage(), e);
        return new ErrorMessageResponse("Error: " + e.getMessage());
    }
}
