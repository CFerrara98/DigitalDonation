package it.unisa.is.c09.digitaldonation.googledriveapi;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Classe che implementa la logica di business per caricare le immagini su Google Drive.
 *
 * @author Elpidio Mazza
 */
@Service
public class GoogleDriveService implements GoogleDriveServiceInterface {

  private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveService.class);

  @Value("${google.service_account_email}")
  private String serviceAccountEmail;

  @Value("${google.application_name}")
  private String applicationName;

  @Value("${google.service_account_key}")
  private String serviceAccountKey;

  @Value("${google.folder_id}")
  private String folderId;

  /**
   * Metodo che restituisce il service per la connessione a Google Drive.
   *
   * @return Drive è il Drive.
   */
  public Drive getDriveService() {
    Drive service = null;
    try {
      URL resource = GoogleDriveService.class.getResource("/" + this.serviceAccountKey);
      java.io.File key = Paths.get(resource.toURI()).toFile();
      HttpTransport httpTransport = new NetHttpTransport();
      JacksonFactory jsonFactory = new JacksonFactory();

      GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
              .setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
              .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
              .setServiceAccountPrivateKeyFromP12File(key).build();
      service = new Drive.Builder(httpTransport, jsonFactory,
              credential).setApplicationName(applicationName)
              .setHttpRequestInitializer(credential).build();
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    return service;
  }

  /**
   * Metodo che permette di caricare l'immagine su Drive.
   *
   * @param fileName è il nome del file.
   * @param filePath è il path del file.
   * @param mimeType è il MIME dell'immagine.
   * @return File ritorna il File;
   */
  @Override
  public File upLoadFile(String fileName, String filePath, String mimeType) {
    File file = new File();
    try {
      java.io.File fileUpload = new java.io.File(filePath);
      com.google.api.services.drive.model.File fileMetadata =
              new com.google.api.services.drive.model.File();
      fileMetadata.setMimeType(mimeType);
      fileMetadata.setName(fileName);
      fileMetadata.setParents(Collections.singletonList(folderId));
      com.google.api.client.http.FileContent fileContent = new FileContent(mimeType, fileUpload);
      file = getDriveService().files().create(fileMetadata, fileContent)
              .setFields("id,webContentLink,webViewLink").execute();
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    return file;
  }

  /**
   * https://drive.google.com/file/d/18dTDFIxhBleEiayTwhtpz0oNKiuVqm7u/view?usp=drivesdk
   * https://drive.google.com/uc?export=view&id=18dTDFIxhBleEiayTwhtpz0oNKiuVqm7u
   * Esempio di immagine Google Drive Image
   * Original URL: https://drive.google.com/file/d/0B6wwyazyzml-OGQ3VUo0Z2thdmc/view
   * Bisogna copiare l'id dall'URL originale.
   * (tra /d e /view), e va messo nell'URL:
   * https://drive.google.com/uc?export=view&id=0B6wwyazyzml-OGQ3VUo0Z2thdmc
   */
  public String pathSavedToView(String path) {
    String startingPath = "https://drive.google.com/uc?export=view&id=";
    String subPath = path.substring(path.lastIndexOf("/d/") + 3, path.lastIndexOf("/view"));
    return startingPath + subPath;
  }
}
