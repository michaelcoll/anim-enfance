package fr.animenfance.partenaire.dao;

import fr.animenfance.bean.partenaire.Partenaire;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PartenaireDao {

  Partenaire getById(@Param("id") Integer id);

  List<Partenaire> list();

  int create(@Param("partenaire") Partenaire partenaire);

  int deleteById(@Param("id") Integer id);
}
