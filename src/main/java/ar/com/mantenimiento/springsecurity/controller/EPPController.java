package ar.com.mantenimiento.springsecurity.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.EPPDTO;
import ar.com.mantenimiento.entity.Epp;
import ar.com.mantenimiento.springsecurity.dao.impl.EPPDAO;
import ar.com.mantenimiento.utility.AppConfig;
import ar.com.mantenimiento.utility.ImageConverterUtility;

@Controller
@Transactional
public class EPPController {

//	private static String UPLOAD_LOCATION = "C:/mytemp/";
//	private static String UPLOAD_LOCATION;

	@Autowired
	private EPPDAO EPPDAO;

	@Autowired
	private Mapper dozerMapper;

	@Autowired
	private AppConfig appConfig;
	
	
	
	public EPPController(){
		
		
		
	}
	
	@RequestMapping(value = "admin/formAgregarEPP.htm", method = RequestMethod.GET)
	public ModelAndView getSingleUploadPage(ModelMap model) {
		ModelAndView mav = new ModelAndView("admin/formularios/formularioAgregarEPP");

		return mav;
	}

	@RequestMapping("admin/subirArchivo.htm")
	public ModelAndView guardarArchivo(@RequestParam("file") MultipartFile file, @RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion) throws IOException {
		String url = appConfig.pathIagen() + file.getOriginalFilename();
		File archivo = new File(url);
		
		Epp epp = new Epp();
		epp.setDescripcion(descripcion);
		epp.setImagen(url);
		epp.setNombre(nombre);

		FileUtils.writeByteArrayToFile(archivo, file.getBytes());
		
		File sourceimage = new File(epp.getImagen());
		BufferedImage image = ImageIO.read(sourceimage);
		BufferedImage resizeImage = resizeImage(image,75,75);
		archivo.delete();
		  ImageIO.write(resizeImage, "jpg", archivo);
		
		EPPDAO.persist(epp);
		ModelAndView mav = new ModelAndView("admin/exito/exito");
		mav.addObject("mensaje", "EPP cargado con exito!");
		return mav;

	}

    public static BufferedImage resizeImage(final Image image, int width, int height) {
    int targetw = 0;
    int targeth = 75;

    if (width > height)targetw = 112;
    else targetw = 50;

    do {
        if (width > targetw) {
            width /= 2;
            if (width < targetw) width = targetw;
        }

        if (height > targeth) {
            height /= 2;
            if (height < targeth) height = targeth;
        }
    } while (width != targetw || height != targeth);

    final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    final Graphics2D graphics2D = bufferedImage.createGraphics();
    graphics2D.setComposite(AlphaComposite.Src);
    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.drawImage(image, 0, 0, width, height, null);
    graphics2D.dispose();

    return bufferedImage;
}

    
    
    @RequestMapping("admin/eliminarEppModalObligatorio.htm")
    public ModelAndView eliminarEppModalObligatorio(int[] victim) throws IOException{
    	

		ModelAndView mav = new ModelAndView("admin/formularios/EPPModal");

		List<Epp> epps = EPPDAO.getAll(Epp.class);
		
		List<Epp> victims = new ArrayList<>();
		
		
		for (Epp epp : epps) {
			
			for(int i = 0 ; i<victim.length; i++){
				
				if(epp.getIdEpp() == victim[i]){
					victims.add(epp);
					
				}
				
			}
			
		}
		
		
		

		List<EPPDTO> eppdtos = new ArrayList<EPPDTO>();
		for (Epp epp : victims) {

			
			String convertImage = ImageConverterUtility.convertImage(epp.getImagen());
			
			
//			File sourceimage = new File(epp.getImagen());
//			BufferedImage image = ImageIO.read(sourceimage);

//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(image, "jpg", baos);
			EPPDTO eppdto = dozerMapper.map(epp, EPPDTO.class);
//			byte[] encode = Base64.getEncoder().encode(baos.toByteArray());
//			eppdto.setImagen(new String(encode, "UTF-8"));
//			eppdto.setByteImg(encode);
			eppdto.setImagen(convertImage);
			eppdtos.add(eppdto);

		}

		mav.addObject("epps", eppdtos);

		return mav;
    	
    	
    	
    }
    
	@RequestMapping("admin/eppModal.htm")
	public ModelAndView eppModal() throws IOException {

		ModelAndView mav = new ModelAndView("admin/formularios/EPPModal");

		List<Epp> epps = EPPDAO.getAll(Epp.class);

		List<EPPDTO> eppdtos = new ArrayList<EPPDTO>();
		for (Epp epp : epps) {

			
			String convertImage = ImageConverterUtility.convertImage(epp.getImagen());
			
			
//			File sourceimage = new File(epp.getImagen());
//			BufferedImage image = ImageIO.read(sourceimage);

//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(image, "jpg", baos);
			EPPDTO eppdto = dozerMapper.map(epp, EPPDTO.class);
//			byte[] encode = Base64.getEncoder().encode(baos.toByteArray());
//			eppdto.setImagen(new String(encode, "UTF-8"));
//			eppdto.setByteImg(encode);
			eppdto.setImagen(convertImage);
			eppdtos.add(eppdto);

		}

		mav.addObject("epps", eppdtos);

		return mav;

	}

	// @RequestMapping(value = "/imageController/{imageId}")
	// @ResponseBody
	// public byte[] helloWorld(@PathVariable long imageId) {
	// Image image = null;//obtain Image instance by id somehow from
	// DAO/Hibernate
	// return image.b
	// }

}
