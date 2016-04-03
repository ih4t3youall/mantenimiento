package ar.com.mantenimiento.springsecurity.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Base64;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;


@Transactional
@Controller
public class PdfController{
	
	
	
	@Autowired
	private MaquinaDAO maquinaDAO;
	
	
	
	@RequestMapping("operario/generarPdf.htm")
	public @ResponseBody ModelAndView generarPdf(int idMaquina) throws MalformedURLException, IOException{
		
		
		 Maquina maquina = maquinaDAO.getByKey(idMaquina);
		
		
			
		 ModelAndView mav = new ModelAndView();
		 if(maquina.getUrlPdf() != null){
			 mav.setViewName("pdf/mostrarPdf");
		InputStream is = new FileInputStream(maquina.getUrlPdf());
	    byte[] pdfBytes = IOUtils.toByteArray(is);
	    mav.addObject("pdf",new String(Base64.getEncoder().encode(pdfBytes)));
		
		 }else{
			 
			 mav.setViewName("pdf/pdfNoNecontrado");
			 
		 }
		
		return mav;
		
		
		
		
		
	}
	
	@RequestMapping("user/generarPdf.htm")
	public @ResponseBody ModelAndView generarPdfUser(int idMaquina) throws MalformedURLException, IOException{
		
		
		
		
		return generarPdf(idMaquina);
		
		
		
		
		
	}
	
	
	
	
	
	
}