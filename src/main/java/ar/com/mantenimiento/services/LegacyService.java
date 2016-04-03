package ar.com.mantenimiento.services;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.mantenimiento.dto.FormDTO;
import ar.com.mantenimiento.entity.FormItem;
import ar.com.mantenimiento.entity.FormItemLegacy;
import ar.com.mantenimiento.entity.FormLegacy;
import ar.com.mantenimiento.springsecurity.dao.impl.FormLegacyDAO;
import ar.com.mantenimiento.utility.FechaUtility;

@Service("legacyService")
public class LegacyService {
	
	@Autowired
	private Mapper dozerMapper;
	
	@Autowired
	private FormLegacyDAO formLegacyDAO;
	
	
	public void saveFormLegacy(FormDTO formDTO){
		
		
		FormLegacy legacy = dozerMapper.map(formDTO, FormLegacy.class);
		legacy.setLastModifyDate(FechaUtility.getFechaActual());
		legacy.setIdMaquina(formDTO.getMaquina().getId());
		legacy.setIdProyecto(formDTO.getProyecto().getId());
		legacy.setIdEmpresa(formDTO.getEmpresa().getId());
//		legacy.setFechaRealizacion(FechaUtility.deStringToDate(formDTO.getFechaRealizacion()));
//		legacy.setFechaProgramada(FechaUtility.deStringToDate(formDTO.getFechaProgramada()));
		
		
		for (FormItem formItem : formDTO.getFormItems()) {
			
			
			FormItemLegacy map = dozerMapper.map(formItem, FormItemLegacy.class);
			
			legacy.addItem(map);
			
			
			
		}
		
		
		System.out.println("llente todo");
		formLegacyDAO.persist(legacy);
		//FIXME
		System.out.println("inserte en base de datos legacy");
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
