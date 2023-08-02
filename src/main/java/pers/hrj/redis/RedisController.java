package pers.hrj.redis;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hrj.model.entity.User;
import pers.hrj.model.vo.SimpleRestVo;
import pers.hrj.service.UserService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @GetMapping("/get")
    public SimpleRestVo get(){
        User user = userService.getBaseMapper().selectById(1686311752059142146L);
//        redisTemplate.opsForValue().set("user", JSONObject.toJSONString(user),10, TimeUnit.SECONDS);
//        User o = JSONObject.parseObject(redisTemplate.opsForValue().get("user").toString(),User.class);
//        System.out.println(o);

        Map<String, Object> map = BeanUtil.beanToMap(user);
        map.forEach((k,v)->{
            if (null != v) map.put(k, String.valueOf(v));
        });
        redisTemplate.opsForHash().putAll("user",map);
        redisTemplate.expire("user",10,TimeUnit.SECONDS);
        Object o = redisTemplate.opsForHash().entries("user");
        return SimpleRestVo.successVo(o);
    }
}
