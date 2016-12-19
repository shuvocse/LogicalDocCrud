package com.csi.sample;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tanmoy Mandal on 12/15/2016.
 */
public class FolderDTO {
    @Size(min = 4,max = 15,message = "Folder Name size has to be 4 to 15")
    @NotNull()
    @NotEmpty(message = "Cannot be Empty")
    @NotBlank(message = "Cannot be Empty")
    private  String folderName;
    private  String folderID;

    public FolderDTO() {
    }

    public FolderDTO(String folderName, String folderID) {
        this.folderName = folderName;
        this.folderID = folderID;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName.trim();
    }

    public String getFolderID() {
        return folderID.trim();
    }

    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }
}
