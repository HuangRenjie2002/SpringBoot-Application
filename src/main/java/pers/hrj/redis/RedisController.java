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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
        User user = userService.getBaseMapper().selectById(1686311752046559233L);
//        redisTemplate.opsForValue().set("user", JSONObject.toJSONString(user),10, TimeUnit.SECONDS);
//        User o = JSONObject.parseObject(redisTemplate.opsForValue().get("user").toString(),User.class);
//        System.out.println(o);

//        Map<String, Object> map = BeanUtil.beanToMap(user);
//        map.forEach((k,v)->{
//            if (null != v) map.put(k, String.valueOf(v));
//        });
//        redisTemplate.opsForHash().putAll("user",map);
//        redisTemplate.expire("user",10,TimeUnit.SECONDS);
//        Object o = redisTemplate.opsForHash().entries("user");
//        redisTemplate.opsForSet().add("a","1","2","1");
//        Object a = redisTemplate.opsForSet().members("a");
//        redisTemplate.opsForZSet().add("a","a",1);
//        redisTemplate.opsForZSet().add("a","b",2);
//        Set a = redisTemplate.opsForZSet().range("a", 0, 2);
        String a =  "[1,2,3]";
        Set set1 = JSONObject.parseObject(a, Set.class);
        System.out.println(set1.contains(1));
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
//        System.out.println(JSONObject.parseObject(user.getEmail(),Set.class));
        user.setEmail(JSONObject.toJSONString(set));
        userService.updateById(user);
        return SimpleRestVo.successVo();
    }
}
