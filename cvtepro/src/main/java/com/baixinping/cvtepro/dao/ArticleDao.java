package com.baixinping.cvtepro.dao;


import com.baixinping.cvtepro.entity.ArticleModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ArticleDao extends BaseDao<ArticleModel>{

}
