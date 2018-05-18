package fr.animenfance.creche.dao;

import fr.animenfance.bean.creche.Creche;
import fr.animenfance.common.dao.CRUDDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrecheDao extends CRUDDao<Creche, Integer> {

}
