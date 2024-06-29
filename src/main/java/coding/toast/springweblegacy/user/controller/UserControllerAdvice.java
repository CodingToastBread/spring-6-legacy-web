package coding.toast.springweblegacy.user.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice(basePackageClasses = UserController.class)
@RequiredArgsConstructor
public class UserControllerAdvice {

    JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
    private static final Logger log = LoggerFactory.getLogger(UserControllerAdvice.class);
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      MethodArgumentNotValidException ex) {
        // get Accept Header value from HttpServletRequest
        // and return proper contents using HttpServletResponse
        ArrayNode jsonNodes = nodeFactory.arrayNode();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            
            // hibernate message interpolate only works on default message! can use {value}, {min}... etc!
            log.error("get error message using hibernate Message interpolate : {}", error.getDefaultMessage());
            
            // message source cant use hibernate message interpolate! that is why is use {1}, not {value}
            log.error("get error message using messageSource's Message Formatter: {}", message);
            jsonNodes.add(message);
        });
        
        try {
            // for now, i'll just return json contents
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(jsonNodes.toString());
            log.error("error message = {}", jsonNodes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
