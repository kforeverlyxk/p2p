package com.offcn.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.schemas.office.x2006.encryption.CTDataIntegrity;
import com.offcn.dao.CityDao;
import com.offcn.domain.City;
import com.offcn.service.CityService;

@Service
@Transactional
public class CityServiceImp  implements  CityService {

	@Autowired
	private CityDao  cityDao;
	public List<City> findPrivers() {
		// TODO Auto-generated method stub
		return cityDao.findByParentCityAreaNumIsNull();
	}
	public List<City> findByParentCityAreaNum(String cityAreaNum) {
		// TODO Auto-generated method stub
		return cityDao.findByParentCityAreaNum(cityAreaNum);
	}
	

}
