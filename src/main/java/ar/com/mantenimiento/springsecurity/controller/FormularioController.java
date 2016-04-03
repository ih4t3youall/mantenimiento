package ar.com.mantenimiento.springsecurity.controller;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.mantenimiento.dto.FormDTO;
import ar.com.mantenimiento.entity.Form;
import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.springsecurity.dao.impl.FormDAO;
import ar.com.mantenimiento.springsecurity.dao.impl.FormItemDAO;
import ar.com.mantenimiento.utility.GsonUtility;

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

	@RequestMapping("admin/editarFormulario.htm")
	public ModelAndView editarFormulario() {
		ModelAndView mav = new ModelAndView("admin/formularios/editarFormulario");

		return mav;

	}

	@RequestMapping("admin/formEditarFormulario.htm")
	public ModelAndView formEditarFormulario(int idMaquina) {
		ModelAndView mav = new ModelAndView("admin/formularios/formEditarFormulario");
		
		Form form = formDAO.findFormByMaquinaId(idMaquina);
		
		FormDTO formDTO = dozerMapper.map(form, FormDTO.class);
		mav.addObject("form",formDTO);
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
