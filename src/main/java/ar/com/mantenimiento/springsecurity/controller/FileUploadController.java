package ar.com.mantenimiento.springsecurity.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import ar.com.mantenimiento.utility.FileBucket;
import ar.com.mantenimiento.utility.FileValidator;
import ar.com.mantenimiento.utility.MultiFileBucket;
import ar.com.mantenimiento.utility.MultiFileValidator;
 
@Controller
@ComponentScan(basePackages = { "ar.com.mantenimiento.*" })
@PropertySource("classpath:filesystem.properties")
public class FileUploadController {
 
	@Value("${path.images}")
    private static String UPLOAD_LOCATION;
//	private static String UPLOAD_LOCATION="C:/mytemp/";
 
    @Autowired
    FileValidator fileValidator;
 
    @Autowired
    MultiFileValidator multiFileValidator;
    
    
    
    
    
    
    
 
    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
 
    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }
 
 
    @RequestMapping(value = "admin/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "singleFileUploader";
    }
 
    @RequestMapping(value = "admin/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ModelMap model) throws IOException {
 
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "singleFileUploader";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileBucket.getFile();
 
            // Now do something with file...
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            return "success";
        }
    }
 
    @RequestMapping(value = "admin/multiUpload", method = RequestMethod.GET)
    public String getMultiUploadPage(ModelMap model) {
        MultiFileBucket filesModel = new MultiFileBucket();
        model.addAttribute("multiFileBucket", filesModel);
        return "multiFileUploader";
    }
 
    @RequestMapping(value = "admin/multiUpload", method = RequestMethod.POST)
    public String multiFileUpload(@Valid MultiFileBucket multiFileBucket,
            BindingResult result, ModelMap model) throws IOException {
 
        if (result.hasErrors()) {
            System.out.println("validation errors in multi upload");
            return "multiFileUploader";
        } else {
            System.out.println("Fetching files");
            List<String> fileNames = new ArrayList<String>();
            // Now do something with file...
            for (FileBucket bucket : multiFileBucket.getFiles()) {
                FileCopyUtils.copy(bucket.getFile().getBytes(), new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
                fileNames.add(bucket.getFile().getOriginalFilename());
            }
 
            model.addAttribute("fileNames", fileNames);
            return "multiSuccess";
        }
    }
    
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
 
}