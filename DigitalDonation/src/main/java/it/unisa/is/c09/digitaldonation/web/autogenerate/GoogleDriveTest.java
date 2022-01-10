package it.unisa.is.c09.digitaldonation.web.autogenerate;


import it.unisa.is.c09.digitaldonation.googledriveapi.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


@Controller
public class GoogleDriveTest {

  @Autowired
  GoogleDriveService googleDriveService;

  private Logger logger = Logger.getLogger("GoogleDriveAPI");

  @RequestMapping(value = "/testUpload", method = RequestMethod.GET)
  public String upladFile(HttpServletRequest request, Model model) {
    logger.info("Looking for file");

    File file = new File("C:\\Users\\elpid\\Documents\\GitHub\\DigitalDonation\\DigitalDonation\\src\\main\\resources\\bucky.png");
    //File file = new File("/src/main/resources/bucky.png");
    //File file = new File("demo.txt");
    logger.info("Try to upload.....");
    com.google.api.services.drive.model.File file2 = googleDriveService.upLoadFile(file.getName(), file.getAbsolutePath(), "image/jpg");
    logger.info("File uploaded");
    logger.info(file2.getWebViewLink());
    try {
      System.err.println(file2.toPrettyString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


}
