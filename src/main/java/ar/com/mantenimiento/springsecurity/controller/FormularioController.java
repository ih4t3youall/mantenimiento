package ar.com.mantenimiento.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.EPPDTO;
import ar.com.mantenimiento.dto.FormDTO;
import ar.com.mantenimiento.entity.Form;
import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.springsecurity.dao.impl.EPPDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormItemDAO;
import ar.com.mantenimiento.utility.GsonUtility;
import ar.com.mantenimiento.utility.ImageConverterUtility;

@Controller
@Transactional
public class FormularioController {
	
	
	
	@Autowired
	private FormDAO formDAO;
	
	@Autowired 
	private FormItemDAO formItemDAO;
	
	@Autowired
	private GsonUtility gsonUtility;
	
	@Autowired
	private Mapper dozerMapper;
	
	@Autowired
	private EPPDAO EPPDAO;

	@RequestMapping("admin/editarFormulario.htm")
	public ModelAndView editarFormulario() {
		ModelAndView mav = new ModelAndView("admin/formularios/editarFormulario");

		return mav;

	}

	@RequestMapping("admin/formEditarFormulario.htm")
	public ModelAndView formEditarFormulario(int idMaquina) {
//		ModelAndView mav = new ModelAndView("admin/formularios/formEditarFormulario");
		ModelAndView mav = new ModelAndView("admin/formularios/editarTemplateFormulario");
		
		Form form = formDAO.findFormByMaquinaId(idMaquina);
		
		FormDTO formDTO = dozerMapper.map(form, FormDTO.class);
		mav.addObject("form",formDTO);
		mav.addObject("idMaquina",formDTO.getMaquina().getId());
		
		List<EPPDTO> epps = EPPDAO.findEppByFormId(form.getId());

		List<EPPDTO> obligatorio = new ArrayList<EPPDTO>();
		List<EPPDTO> opcional = new ArrayList<EPPDTO>();
		// FIXME epp dto sacar esto a un utility
		for (EPPDTO eppdto : epps) {

			eppdto.setImagen(ImageConverterUtility.convertImage(eppdto.getImagen()));
			if (eppdto.isObligatorio()) {

				obligatorio.add(eppdto);

			} else {

				opcional.add(eppdto);

			}

		}

		mav.addObject("obligatorio", obligatorio);
		mav.addObject("opcional", opcional);
		
		
		return mav;

	}
	
	
	
	@RequestMapping("admin/submitFormEditarFormulario.htm")
	public ModelAndView submitFormEditarFormulario(String formItems,int maquinaId){
		
		Form form = formDAO.findFormByMaquinaId(maquinaId);
		
		FormItem[] fromJson = gsonUtility.getGson().fromJson(formItems, FormItem[].class);
		form.getFormItems().clear();
		for (FormItem formItem : fromJson) {
			
		
			form.addFormItem(formItem);
			
			
		}
		formDAO.persist(form);
		ModelAndView mav = new ModelAndView("admin/exito/exito");
		mav.addObject("mensaje","Exito al modoficar el formulario");
		return mav;
	}
	


}
