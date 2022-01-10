package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.gestionetesserinomanagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Operatore;
import it.unisa.is.c09.digitaldonation.model.entity.Tesserino;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;
import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.utentemanagement.UtenteService;
import it.unisa.is.c09.digitaldonation.utils.forms.LoginForm;
import it.unisa.is.c09.digitaldonation.utils.forms.LoginFormValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Controller per la gestione dell'utenza.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 */
@Controller
public class UtenteController {
  @Autowired
  UtenteService utenteService;
  @Autowired
  GestioneTesserinoService gestioneTesserinoService;
  @Autowired
  LoginFormValidate loginFormValidator;

  private Logger logger = Logger.getLogger(String.valueOf(UtenteController.class));

  /**
   * Metodo per la visualizzazione dell'homepage o della dashboard
   *
   * @param model è l'oggetto Model.
   * @return String stringa che rappresenta la pagina da visualizzare.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String visualizzaHome(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente != null) {
      if (utente instanceof Operatore) return "GUIGestioneUtente/dashboardOperatore";
      if (utente instanceof Donatore) {
        Donatore donatore = (Donatore) utente;
        try {
          Tesserino tesserino = gestioneTesserinoService.aggiornaTesserino(donatore);
          model.addAttribute("tesserino", tesserino);
        } catch (CannotSaveDataRepositoryException e) {
          e.printStackTrace();
          return "GUIGestioneUtente/dashboardDonatore";
        }
        return "GUIGestioneUtente/dashboardDonatore";
      }
    }
    request.getSession().invalidate();
    return "GUIGestioneUtente/homepage";
  }


  /**
   * Metodo che permette di andare alla pagina di login, risponde a richieste GET.
   *
   * @param loginForm         è il form compilato dall'utente.
   * @param result            sono i campi del form.
   * @param redirectAttribute è un attributo che permette di cambiare pagina.
   * @param model             è l'oggetto Model.
   * @return String ridirezione alla pagina.
   */
  @RequestMapping(value = "/goLogin", method = RequestMethod.GET)
  public String goLogin(HttpServletRequest request, @ModelAttribute("loginModel") LoginForm loginForm,
                        BindingResult result, RedirectAttributes redirectAttribute, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null) {
      model.addAttribute("loginForm", new LoginForm());
      return "GUIGestioneUtente/login";
    }
    if (utente != null) {
      //Se è un operatore
      if (utente instanceof Operatore) {
        request.getSession().setAttribute("utente", utente);
        return "GUIGestioneUtente/dashboardOperatore";
      }
      //Se è un donatore
      else if (utente instanceof Donatore) {
        request.getSession().setAttribute("utente", utente);
        return "GUIGestioneUtente/dashboardDonatore";
      }
    }
    return "redirect:/goLogin";
  }

  /**
   * Questo metodo permette di effettuare il login ad un utente registrate.
   *
   * @param request           è la richiesta inviata dall'utente.
   * @param loginForm         è il form compilato dall'utente.
   * @param result            sono i campi del form.
   * @param redirectAttribute è un attributo che permette di cambiare pagina.
   * @param model             è l'oggetto Model.
   * @return String che ridireziona ad una pagina.
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(HttpServletRequest request, @ModelAttribute("loginForm") LoginForm loginForm,
                      BindingResult result, RedirectAttributes redirectAttribute, Model model) {

    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null) {
      loginFormValidator.validate(loginForm, result);
      if (result.hasErrors()) {
        // se ci sono errori il metodo controller setta tutti i parametri
        redirectAttribute.addFlashAttribute("EmailError", "Email o password errati, per favore riprova");
        return "redirect:/goLogin";
      }
      try {
        utente = utenteService.login(loginForm.getEmail(), loginForm.getPassword());
      } catch (UserNotLoggedException e) {
        redirectAttribute.addFlashAttribute("EmailPrecedente", loginForm.getEmail());
        redirectAttribute.addFlashAttribute("PasswordError", "Email o password errati, per favore riprova");
        //model.addAttribute("utente",utente);
        return "redirect:/goLogin";
      } catch (NoSuchAlgorithmException e) {
        return "redirect:/goLogin";
      }
    }
    if (utente != null) {
      //Se è un operatore
      if (utente instanceof Operatore) {
        request.getSession().setAttribute("utente", utente);
        return "GUIGestioneUtente/dashboardOperatore";
      }
      //Se è un donatore
      else if (utente instanceof Donatore) {
        request.getSession().setAttribute("utente", utente);
        Tesserino tesserino = null;
        try {
          tesserino = gestioneTesserinoService.aggiornaTesserino(utente);
        } catch (CannotSaveDataRepositoryException e) {
          return "redirect:/";
        }
        request.getSession().setAttribute("tesserino", tesserino);
        return "GUIGestioneUtente/dashboardDonatore";
      }
    }
    return "redirect:/goLogin";
  }

  /**
   * Metodo che permette ad un utente di effettuare il logout
   *
   * @param request è la richiesta inviata dall'utente.
   * @return String che ridireziona ad una pagina.
   */
  @RequestMapping(value = "/logout")
  public String logout(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente != null) {
      try {
        utenteService.logout(utente);
      } catch (AccessNotAuthorizedException e) {
        request.getSession().setAttribute("codiceErrore", 401);
        return "redirect:/error";
      }
    }
    request.getSession().invalidate();
    return "GUIGestioneUtente/homepage";
  }

  /**
   * Metodo che permette di andare alla dashboard del donatore.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina.
   */
  @RequestMapping(value = "/dashboardDonatore", method = RequestMethod.GET)
  public String dashboardDonatore(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente instanceof Donatore) {
      model.addAttribute("utente", request.getSession().getAttribute("utente"));
      Tesserino tesserino = null;
      try {
        tesserino = gestioneTesserinoService.aggiornaTesserino(utente);
      } catch (CannotSaveDataRepositoryException e) {
        return "redirect:/";
      }
      model.addAttribute("tesserino", tesserino);
      return "GUIGestioneUtente/dashboardDonatore";
    }
    request.getSession().setAttribute("codiceErrore", 401);
    return "redirect:/error";
  }

  /**
   * Metodo che permette di andare alla dashboard dell'operatore.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina.
   */
  @RequestMapping(value = "/dashboardOperatore", method = RequestMethod.GET)
  public String dashboardOperatore(HttpServletRequest request, Model model) {
    String successo = request.getParameter("success");
    model.addAttribute("success", successo);
    if (request.getSession().getAttribute("utente") instanceof Operatore) {
      return "GUIGestioneUtente/dashboardOperatore";
    }
    request.getSession().setAttribute("codiceErrore", 401);
    return "redirect:/error";
  }

  /**
   * Metodo che permette di andare alla pagina del team.
   *
   * @return String ridirezione alla pagina.
   */
  @RequestMapping(value = "/goAboutUs", method = RequestMethod.GET)
  public String aboutUs(HttpServletRequest request, Model model) {
    return "GUIGestioneUtente/aboutUs";
  }
}
