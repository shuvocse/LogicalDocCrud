package com.csi.sample;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanmoy Mandal on 12/15/2016.
 */
@Service
public class LogidocService {
    @Autowired
    private LogidocComponent logidocComponent;

    public void createFolder(String folderName){
        logidocComponent.createFolder(logidocComponent.accessLogidoc(),folderName);
    }

    public List<FolderDTO> getFolders(){
        ItemIterable<CmisObject> folders=logidocComponent.getFolders(logidocComponent.accessLogidoc());
        List<FolderDTO> folderList = new ArrayList<FolderDTO>();
        for (CmisObject folder : folders) {
            FolderDTO folDto = new FolderDTO(folder.getName(),folder.getId().split("\\.")[1]);
            folderList.add(folDto);
        }
        return folderList;
    }

    public String getFolderName(String folderID){
        return logidocComponent.getFolderName(logidocComponent.accessLogidoc(),folderID);
    }

    public void  renameFolder(FolderDTO folderDTO){
        logidocComponent.renameFolder(logidocComponent.accessLogidoc(), folderDTO);
    }

    public void deleteFolder(String folderID){
        logidocComponent.deleteFolder(logidocComponent.accessLogidoc(),folderID);
    }
}
