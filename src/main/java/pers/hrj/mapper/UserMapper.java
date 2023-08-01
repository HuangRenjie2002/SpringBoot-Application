package pers.hrj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.hrj.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
