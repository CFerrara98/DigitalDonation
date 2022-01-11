package it.unisa.is.c09.digitaldonation.googledriveapi;

import com.google.api.services.drive.model.File;

/**
 * Interfaccia che fornisce i metodi per caricare le immagini su Google Drive.
 *
 * @author Elpidio Mazza
 */
public interface GoogleDriveServiceInterface {

  File upLoadFile(String fileName, String filePath, String mimeType);
}
