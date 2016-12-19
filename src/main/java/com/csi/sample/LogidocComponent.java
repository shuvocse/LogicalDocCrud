package com.csi.sample;

import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tanmoy Mandal on 12/15/2016.
 */
@Component("LogidocComponent")
public class LogidocComponent {
    public Session accessLogidoc(){
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        //hash map variable for logidoc information
        Map parameter = new HashMap();
        parameter.put(SessionParameter.USER, "admin");
        parameter.put(SessionParameter.PASSWORD, "admin");
        parameter.put(SessionParameter.ATOMPUB_URL, "http://localhost:8090/service/cmis");
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
        parameter.put(SessionParameter.REPOSITORY_ID,"5");
        //connect with logidoc and create session for the repository
        Session session = sessionFactory.createSession(parameter);

        return session;
    }

    public void createFolder(Session session, String folderName){
        //get default folder by path
        Folder defaultFolder = (Folder) session.getObjectByPath("/Default");

        //hash map variable for newfolder information
        Map<String, String> newFolderInfo = new HashMap<String, String>();
        newFolderInfo.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
        newFolderInfo.put(PropertyIds.NAME, folderName);

        //create newfolder under Default folder
        defaultFolder.createFolder(newFolderInfo);
    }

    public ItemIterable<CmisObject> getFolders(Session session){
        Folder defaultFolder = (Folder) session.getObjectByPath("/Default");
        return defaultFolder.getChildren();
    }

    public String getFolderName(Session session, String folderID){
        return session.getObject(folderID).getName();
    }

    public void  renameFolder(Session session, FolderDTO folderDTO){
        session.getObject(folderDTO.getFolderID()).rename(folderDTO.getFolderName());
    }

    public  void deleteFolder(Session session,String folderID){
        session.getObject(folderID).delete();
    }
}
