package it.unisa.is.c09.digitaldonation.web.error;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    /**
     * Mappa i vari errori http response
     * @return
     */
    @RequestMapping(value = "/error")
    public String  renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("errorPage");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        if(httpErrorCode!=-1){
            switch (httpErrorCode) {
                case 400: {
                    errorMsg = "Http Error Code: 400. Bad Request";
                    break;
                }
                case 401: {
                    errorMsg = "Http Error Code: 401. Unauthorized";
                    break;
                }
                case 404: {
                    errorMsg = "Http Error Code: 404. Resource not found";
                    break;
                }
                case 500: {
                    errorMsg = "Http Error Code: 500. Internal Server Error";
                    break;
                }
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return "errorsPages/errorPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return httpRequest.getAttribute("javax.servlet.error.status_code")==null ? -1 :
            (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}