package ar.com.mantenimiento.springsecurity.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.EPPDTO;
import ar.com.mantenimiento.dto.EmpresaDTO;
import ar.com.mantenimiento.dto.FormDTO;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.Epp;
import ar.com.mantenimiento.entity.Form;
import ar.com.mantenimiento.entity.FormHasEpp;
import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.entity.Maquina;
import ar.com.mantenimiento.springsecurity.dao.impl.EPPDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.MaquinaDAO;
import ar.com.mantenimiento.utility.FechaUtility;
import ar.com.mantenimiento.utility.GsonUtility;
import ar.com.mantenimiento.utility.ImageConverterUtility;

@Controller
@Transactional
public class AdminController {

	
	private static String UPLOAD_LOCATION = "C:/mytemp/pdfMaquina/";
	
	@Autowired
	private FormDAO formDAO;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private MaquinaDAO maquinaDAO;

	@Autowired
	private Mapper dozerMapper;

	@Autowired
	private EPPDAO eppDAO;

	@Autowired
	private GsonUtility gsonUtility;

	@RequestMapping("admin/adminIni")
	public ModelAndView adminIni() {

		ModelAndView mav = new ModelAndView("admin/adminIni");

		return mav;

	}

	// arma los options
	@RequestMapping("admin/templateFormulario.htm")
	public ModelAndView templateFormulario() {

		ModelAndView mav = new ModelAndView("admin/formularios/templateFormulario");

		return mav;

	}

	@RequestMapping("admin/getEmpresas.htm")
	public @ResponseBody String getEmpresas() {

		List<Empresa> empresas = empresaDAO.findAllEmpresas();
		List<EmpresaDTO> empresasDTO = convertToDTO(empresas);

		String json = gsonUtility.getGson().toJson(empresasDTO);

		return json;

	}

	private List<EmpresaDTO> convertToDTO(List<Empresa> empresas) {
		List<EmpresaDTO> empresasDTO = new ArrayList<EmpresaDTO>();

		for (Empresa empresa : empresas) {

			EmpresaDTO empresaDTO = dozerMapper.map(empresa, EmpresaDTO.class);

			empresasDTO.add(empresaDTO);
		}

		return empresasDTO;

	}

	// arma el formulario donde se agregan los campos del checklist
	@RequestMapping("admin/getTemplateFormulario.htm")
	public @ResponseBody ModelAndView getTemplateFormulario(int idMaquina) {

		// FIXME controlar que no exista el formulario devuelve null si el
		// formulario no existe
		Form form = formDAO.findFormByMaquinaId(idMaquina);
		ModelAndView mav;

		if (form != null) {

			List<EPPDTO> epps = eppDAO.findEppByFormId(form.getId());

			List<EPPDTO> obligatorio = new ArrayList<EPPDTO>();
			List<EPPDTO> opcional = new ArrayList<EPPDTO>();
			// FIXME sacar esto a un utility
			for (EPPDTO eppdto : epps) {

					eppdto.setImagen(ImageConverterUtility.convertImage(eppdto.getImagen()));
				if (eppdto.isObligatorio()) {

					obligatorio.add(eppdto);

				} else {

					opcional.add(eppdto);

				}

			}

			mav = new ModelAndView("admin/formularios/editarTemplateFormulario");
			FormDTO formDTO = dozerMapper.map(form, FormDTO.class);
			mav.addObject("obligatorio", obligatorio);
			mav.addObject("opcional", opcional);
			mav.addObject("idMaquina", idMaquina);
			mav.addObject("form", formDTO);
		} else {

			mav = new ModelAndView("admin/formularios/editarTemplateFormulario");

			form = new Form();
			form.setMaquina(new Maquina());
			mav.addObject("idMaquina", idMaquina);

		}
		return mav;

	}
	
	@RequestMapping("admin/subirPdfMaquina.htm")
	public ModelAndView guardarArchivo(@RequestParam("file") MultipartFile file, @RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion) throws IOException {
		String url = UPLOAD_LOCATION + file.getOriginalFilename();
		ModelAndView mav = new ModelAndView("admin/exito/exito");
		mav.addObject("mensaje", "EPP cargado con exito!");
		return mav;

	}
	
	
	

	// submit del segundo formulario
	@RequestMapping("admin/submitTemplateFormulario.htm")
	public @ResponseBody ModelAndView submitTemplateFormulario(String camposFormulario, int idMaquina,
			String[] eppOpcional, String[] eppObligatorio, String fechaProgramada,String numeroOrden,String numeroInterno) {

		ModelAndView mav = new ModelAndView("admin/exito/formularioCreadoConExtio");

//		List<FormItem> formItems = convertStringToList(camposFormulario);
			
		FormItem[] formItems = gsonUtility.getGson().fromJson(camposFormulario, FormItem[].class);
		
		Form form = formDAO.findFormByMaquinaId(idMaquina);
		
		if (form != null) {

			populateFormItems(formItems, form);

		} else {

			form = new Form();
			List<FormItem> listaFormItems = new ArrayList<FormItem>();
			for (int i =0;i< formItems.length;i++) {
				formItems[i].setForm(form);
				listaFormItems.add(formItems[i]);
				
			}
			
			
		

			form.setFormItems(listaFormItems);
		}

		Maquina maquina = maquinaDAO.findMaquinaById(idMaquina);
		form.setMaquina(maquina);
		form.setFechaProgramada(FechaUtility.deStringToDateUs(fechaProgramada));
		form.setNroInterno(Integer.parseInt(numeroInterno));
		form.setNroOrden(Integer.parseInt(numeroOrden));
		
		formDAO.persist(form);

		List<FormHasEpp> formHasEpps = new ArrayList<FormHasEpp>();

		for (int i = 0; i < eppOpcional.length; i++) {

			FormHasEpp formHasEpp = new FormHasEpp();

			int eppid = Integer.valueOf(eppOpcional[i]);

			Epp epp = eppDAO.getByKey(eppid);
			formHasEpp.setObligatorio(0);
			formHasEpps.add(formHasEpp);

			formHasEpp.setIdEpp(epp.getIdEpp());
			formHasEpp.setIdForm(form.getId());

		}

		for (int i = 0; i < eppObligatorio.length; i++) {

			FormHasEpp formHasEpp = new FormHasEpp();

			int eppid = Integer.valueOf(eppObligatorio[i]);

			Epp epp = eppDAO.getByKey(eppid);
			formHasEpp.setObligatorio(1);
			formHasEpps.add(formHasEpp);

			formHasEpp.setIdEpp(epp.getIdEpp());
			formHasEpp.setIdForm(form.getId());
		}
		if(formHasEpps.size() == 0){
			FormHasEpp formHasEpp = new FormHasEpp();
			formHasEpp.setIdForm(form.getId());
			formHasEpps.add(formHasEpp);
		}
		

		eppDAO.guardarEPP(formHasEpps);

		return mav;

	}

	private void populateFormItems(FormItem []formItems, Form form) {
	
		
		for(int i = 0;i<formItems.length;i++){
			
			FormItem item = formItems[i];
			boolean flag = false;
			for(FormItem formItem : form.getFormItems()){
				
				if(item.getIdformItem() == formItem.getIdformItem()){
					
					formItem.setLabel(item.getLabel());
					flag=true;
					break;
				}
				
			}
			
			if(!flag){
				
				form.addFormItem(formItems[i]);
				
			}
			
			
		}
		

	}

	private List<FormItem> convertStringToList(String texto) {

		List<FormItem> lista = new ArrayList<FormItem>();
		StringTokenizer st = new StringTokenizer(texto, ",");
		while (st.hasMoreTokens()) {
			FormItem it = new FormItem();
			it.setLabel(st.nextToken());
			lista.add(it);
		}
		return lista;

	}

}
