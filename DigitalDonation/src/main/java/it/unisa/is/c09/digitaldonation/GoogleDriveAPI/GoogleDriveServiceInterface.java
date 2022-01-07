package it.unisa.is.c09.digitaldonation.GoogleDriveAPI;
import com.google.api.services.drive.model.File;


public interface GoogleDriveServiceInterface {

    public File upLoadFile(String fileName, String filePath, String mimeType);
}
