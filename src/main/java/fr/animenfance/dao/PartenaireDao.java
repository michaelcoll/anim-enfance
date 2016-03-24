package fr.animenfance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fr.animenfance.bean.Partenaire;

public interface PartenaireDao {

  Partenaire getById(@Param("id") Integer id);

  List<Partenaire> list();

  int create(@Param("partenaire") Partenaire partenaire);

  int deleteById(@Param("id") Integer id);
}
