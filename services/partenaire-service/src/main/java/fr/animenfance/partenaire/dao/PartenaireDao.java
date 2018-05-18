package fr.animenfance.partenaire.dao;

import fr.animenfance.bean.partenaire.Partenaire;
import fr.animenfance.common.dao.CRUDDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PartenaireDao extends CRUDDao<Partenaire, Integer> {

}
