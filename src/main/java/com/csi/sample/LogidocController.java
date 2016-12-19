package com.csi.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Tanmoy Mandal on 12/14/2016.
 */
@Controller
public class LogidocController {
    @Autowired
    private LogidocService logidocService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<FolderDTO> folderList = logidocService.getFolders();
        model.addAttribute("folderList", folderList);
        return "index";
    }


    @RequestMapping(value = "/createFolder", method = RequestMethod.GET)
    public String createFolder(Model model) {
        model.addAttribute("folderDTO", new FolderDTO());
        model.addAttribute("folderURL","/createFolder");
        return "saveFolder";
    }

    @RequestMapping(value = "/createFolder", method = RequestMethod.POST)
    public String createFolder(@Valid FolderDTO folderDTO, BindingResult result) {

        if (!result.hasErrors()) {
            logidocService.createFolder(folderDTO.getFolderName());
            return "redirect:/";
        }
        else {
            return "saveFolder";
        }
    }

    @GetMapping(value = "/renameFolder/{folderID}")
    public String renameFolder(Model model, @PathVariable String folderID) {
        FolderDTO folderDTO =new FolderDTO(logidocService.getFolderName(folderID), folderID);
        model.addAttribute("folderDTO",folderDTO);
        model.addAttribute("folderURL","/renameFolder/" + folderID);
        return "saveFolder";
    }

    @PostMapping(value = "/renameFolder/{folderID}")
    public String renameFolder(@Valid FolderDTO folderDTO, BindingResult result, @PathVariable String folderID) {
        if (!result.hasErrors()) {
            folderDTO =new FolderDTO(folderDTO.getFolderName(), folderID);
            logidocService.renameFolder(folderDTO);
            return "redirect:/";
        }
        else {
            return"saveFolder";
        }
    }

    @GetMapping(value = "/deleteFolder/{folderID}")
    public String deleteFolder(@PathVariable String folderID) {
        logidocService.deleteFolder(folderID);
        return "redirect:/";
    }

}
