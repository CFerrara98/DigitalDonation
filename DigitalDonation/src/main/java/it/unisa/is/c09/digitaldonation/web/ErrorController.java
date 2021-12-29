package it.unisa.is.c09.digitaldonation.web;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {


    /**
     * Mappa i vari errori http response
     * @param request
     * @return
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errorsPages/error404";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errorsPages/error403";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "errorsPages/error401";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errorsPages/error500";
            }
            else if(statusCode == HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value()) {
                return "errorsPages/error503";
            }
        }
        return "errorsPages/error404";
    }


    @Override
    public String getErrorPath() {
        return null;
    }

}
