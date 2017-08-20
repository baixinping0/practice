package com.baixinping.cvtepro.dao;


import com.baixinping.cvtepro.entity.ProModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProModelDao extends BaseDao<ProModel>{
	public ProModel getByName(String model);
}
