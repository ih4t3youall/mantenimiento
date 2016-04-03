package ar.com.mantenimiento.springsecurity.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.utility.AppConfig;
import ar.com.mantenimiento.utility.GsonUtility;

@Transactional
@Controller
@ComponentScan(basePackages = { "ar.com.mantenimiento*" })
@PropertySource("classpath:filesystem.properties")
@Configuration
public class RestController {

	// private static String UPLOAD_LOCATION = "C:/mytemp/pdf";
	// @Value("${path.pdf}")
	// private static String UPLOAD_LOCATION;

	@Autowired
	private MaquinaDAO maquinaDAO;

	@Autowired
	private Environment environment;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private GsonUtility gsonUtility;

	@Autowired
	private EmpresaDAO empresaDAO;

	public RestController() {

	}

	@RequestMapping(value = "admin/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("idMaquina") String idMaquina) {

		if (uploadfile.getOriginalFilename().equals("")) {
			return new ResponseEntity<>(HttpStatus.OK);

		}

		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String filename = uploadfile.getOriginalFilename();

			String filepath = Paths.get(appConfig.pathPdf(), filename).toString();

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();

			Maquina maquina = maquinaDAO.getByKey(Integer.parseInt(idMaquina));
			maquina.setUrlPdf(filepath);
			maquinaDAO.persist(maquina);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	} // method uploadFile

	@RequestMapping(value = "admin/uploadFileEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFileEmpresa(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("empresaDTO") String empresaDTO) {

		Empresa empresa = gsonUtility.getGson().fromJson(empresaDTO, Empresa.class);

		empresaDAO.persist(empresa);

		if (uploadfile.getOriginalFilename().equals("")) {
			return new ResponseEntity<>(HttpStatus.OK);

		}

		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String filename = uploadfile.getOriginalFilename();

			String filepath = Paths.get(appConfig.pathLogoEmpresa(), filename).toString();
			empresa.setUrlImagen(filepath);
			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();

			// Maquina maquina =
			// maquinaDAO.getByKey(Integer.parseInt(idMaquina));
			// maquina.setUrlPdf(filepath);
			// maquinaDAO.persist(maquina);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		empresaDAO.persist(empresa);
		return new ResponseEntity<>(HttpStatus.OK);
	} // method uploadFile

}