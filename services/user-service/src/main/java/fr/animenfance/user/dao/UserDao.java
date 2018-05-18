package fr.animenfance.user.dao;

import fr.animenfance.bean.user.User;
import fr.animenfance.common.dao.CRUDDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends CRUDDao<User, Integer> {

}
