package pers.hrj.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String addr;
    private Date birthday;
    private String sex;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}