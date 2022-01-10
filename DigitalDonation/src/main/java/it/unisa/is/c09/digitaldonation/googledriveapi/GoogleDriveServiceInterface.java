package it.unisa.is.c09.digitaldonation.googledriveapi;

import com.google.api.services.drive.model.File;


public interface GoogleDriveServiceInterface {

  public File upLoadFile(String fileName, String filePath, String mimeType);

  public File upLoadFile(String fileName, java.io.File file, String mimeType);
}
