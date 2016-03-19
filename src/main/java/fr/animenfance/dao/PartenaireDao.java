package fr.animenfance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fr.animenfance.bean.Partenaire;

public interface PartenaireDao {

  Partenaire getById(@Param("id") Long id);

  List<Partenaire> list();
}
