package app.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import app.model.Greeting;
import app.model.MyUploadForm;
import utils.MediaTypeUtils;

@Controller
public class UploadDownloadController {

    private static final String template = "Hello, %s!";
    private static final String DEFAULT_FILE_NAME = "C:\\Asha_Docs\\Automation.pdf";
    
    @Autowired
    private ServletContext servletContext;
    
    @RequestMapping(value = "/upDown")
    public String homePage() {
  
       return "uploadDownload";
    }

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(String.format(template, name));
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String uploadOneFileHandler(Model model) {
  
       MyUploadForm myUploadForm = new MyUploadForm();
       model.addAttribute("myUploadForm", myUploadForm);
  
       return "uploadOneFile";
    }
  
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadOneFileHandlerPOST(HttpServletRequest request, //
          Model model, //
          @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
  
       return this.doUpload(request, model, myUploadForm);
  
    }
    
    @RequestMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {
 
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
 
        File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length()) //
                .body(resource);
    }
    
    private String doUpload(HttpServletRequest request, Model model, //
            MyUploadForm myUploadForm) {
    
         String description = myUploadForm.getDescription();
         System.out.println("Description: " + description);
    
         // Root Directory.
         String uploadRootPath = request.getServletContext().getRealPath("upload");
         System.out.println("uploadRootPath=" + uploadRootPath);
    
         File uploadRootDir = new File(uploadRootPath);
         // Create directory if it not exists.
         if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
         }
         MultipartFile[] fileDatas = myUploadForm.getFileDatas();
         //
         List<File> uploadedFiles = new ArrayList<File>();
         List<String> failedFiles = new ArrayList<String>();
    
         for (MultipartFile fileData : fileDatas) {
    
            // Client File Name
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);
    
            if (name != null && name.length() > 0) {
               try {
                  // Create the file at server
                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
    
                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                  stream.write(fileData.getBytes());
                  stream.close();
                  //
                  uploadedFiles.add(serverFile);
                  System.out.println("Write file: " + serverFile);
               } catch (Exception e) {
                  System.out.println("Error Write file: " + name);
                  failedFiles.add(name);
               }
            }
         }
         model.addAttribute("description", description);
         model.addAttribute("uploadedFiles", uploadedFiles);
         model.addAttribute("failedFiles", failedFiles);
         return "uploadResult";
      }

}
