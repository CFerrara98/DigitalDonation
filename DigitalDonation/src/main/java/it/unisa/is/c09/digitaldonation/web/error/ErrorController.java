package it.unisa.is.c09.digitaldonation.web.error;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * Classe controller che filtra gli errori.
 *
 * @author Elpidio Mazza
 */

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

  /**
   * Mappa i vari errori http response.
   *
   * @return
   */
  @RequestMapping(value = "/error")
  public String renderErrorPage(HttpServletRequest httpRequest, Model model) {

    ModelAndView errorPage = new ModelAndView("errorPage");
    String errorMsg = "default";
    if (httpRequest.getSession().getAttribute("codiceErrore") != null) {

      int httpErrorCode = (int) httpRequest.getSession().getAttribute("codiceErrore");
      if (httpErrorCode != -1) {
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
      httpRequest.getSession().removeAttribute("codiceErrore");
      model.addAttribute("errorMsg", errorMsg);
      return "errorsPages/errorPage";
    }
    return "errorsPages/errorPage";
  }

  @Override
  public String getErrorPath() {
    return null;
  }
}