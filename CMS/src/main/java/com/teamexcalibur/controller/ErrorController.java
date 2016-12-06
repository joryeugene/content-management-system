
package com.teamexcalibur.controller;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    
    @RequestMapping (value="/error")
    public String customError (HttpServletRequest request,
            HttpServletResponse response, Model model){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String exceptionMessage = null;
        exceptionMessage = httpStatus.getReasonPhrase();
        
        String requestUri = 
                (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null){
            requestUri = "Unknown";
        }
        String message = MessageFormat.format("{0} returned for {1}: {2}", statusCode, requestUri, exceptionMessage);
        model.addAttribute("errorMessage", message);
        return "error";
        
    }

}
