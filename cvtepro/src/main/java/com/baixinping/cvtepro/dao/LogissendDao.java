package com.baixinping.cvtepro.dao;


import com.baixinping.cvtepro.entity.LogissendModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LogissendDao extends BaseDao<LogissendModel>{

	public LogissendModel getBylogisticsId(String logisticsId);

}
