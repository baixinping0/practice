package com.baixinping.cvtepro.dao;


import com.baixinping.cvtepro.entity.CacheModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CacheDao extends BaseDao<CacheModel>{

	public CacheModel getByUrl(String methodKey);
	
}
