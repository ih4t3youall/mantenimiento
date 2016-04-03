package ar.com.mantenimiento.dozer;

import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.mantenimiento.dto.FormLegacyDTO;
import ar.com.mantenimiento.entity.Empresa;
import ar.com.mantenimiento.entity.FormLegacy;
import ar.com.mantenimiento.springsecurity.dao.impl.EmpresaDAO;

public class CustomDozerLegacyDTO implements CustomConverter{

	@Autowired
	private EmpresaDAO empresaDAO;
	
	
	
	@Override
	public Object convert(Object formLegacyDTOAux, Object formLegacyAux, Class<?> arg2, Class<?> arg3) {
		// TODO Auto-generated method stub
//		Date date = (Date) Form;
//		FechaUtility.deStringToDate(fecha)
//		
//		System.out.println(date.toString());
		
		FormLegacy formLegacy = (FormLegacy) formLegacyAux;
		FormLegacyDTO formLegacyDTO = new FormLegacyDTO();
		Empresa empresa = empresaDAO.finEmpresaById(formLegacy.getIdEmpresa());
		
//		formLegacyDTO.setEmpresa(empresa);
		
		
		
		
		
		return formLegacyDTO;
	}

}