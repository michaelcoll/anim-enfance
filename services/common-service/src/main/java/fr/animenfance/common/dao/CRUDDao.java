package fr.animenfance.common.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CRUDDao<T, I> {

  T getById(@Param("id") I id);

  List<T> list();

  int create(@Param("item") T item);

  int deleteById(@Param("id") I id);
}
