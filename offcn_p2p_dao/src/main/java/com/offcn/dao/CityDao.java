package com.offcn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.offcn.domain.City;

public interface CityDao extends  JpaRepository<City, Integer> {
	
//	@Query("select a  from   City  a where a.cityLeve=0")
	public  List<City> findByParentCityAreaNumIsNull();

public List<City> findByParentCityAreaNum(String parentCityAreaNum);
}
