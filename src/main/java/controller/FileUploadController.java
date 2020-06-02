package controller;

import model.MyUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        Object target = dataBinder.getTarget();
        if(target ==null){
            return;
        }
        System.out.println("Target="+target);
        if(target.getClass()== MyUploadForm.class){
            dataBinder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
        }
    }

    @GetMapping("/uploadOneFile")
    public String showUploadOneFile(Model model){
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm",myUploadForm);
        return "uploadOneFile";
    }

    @PostMapping("/uploadOneFile")
    public String uploadOneFileHandler(HttpServletRequest request,
                                       Model model,
                                       @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) throws FileNotFoundException {
            return this.doUpload(request,model,myUploadForm);
    }

    @GetMapping("/uploadMultiFile")
    public String showUploadMultiFile(Model model){
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm",myUploadForm);
        return "uploadMultiFile";
    }
    @PostMapping("/uploadMultiFile")
    public String uploadMultiFileHander(HttpServletRequest request,
                                        Model model,
                                        @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) throws FileNotFoundException {
        return this.doUpload(request,model,myUploadForm);
    }

    private String doUpload(HttpServletRequest request, Model model, MyUploadForm myUploadForm) throws FileNotFoundException {
        String description = myUploadForm.getDescription();
        System.out.println("Description"+ description);
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath="+uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()){
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
        Map<File,String> uploadFiles = new HashMap();
        for(CommonsMultipartFile fileData : fileDatas){
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name="+name);
            if(name!=null && name.length()>0){
                try{
                    File serverFile = new File(uploadRootDir.getAbsolutePath()+File.separator+name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    uploadFiles.put(serverFile,name);
                    System.out.println("Write File:" + serverFile);
                }catch (Exception e){
                    System.out.println("Error write file:"+name);
                }
            }
        }
        model.addAttribute("description"+ description);
        model.addAttribute("uploadFiles",uploadFiles);
        return "uploadResult";
    }
}
