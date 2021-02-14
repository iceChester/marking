package com.iwyu.marking.handler;/**
 * Created by Chester on 6/2/2021.
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.iwyu.marking.entity.TeachingMaterial;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName DeletedMetaObjectHandler
 * @Description 假删除、乐观锁自动赋值配置类
 * @Author XiaoMao
 * @Date 6/2/2021 下午4:08
 * @Version 1.0
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("version",new Long(1),metaObject);
        this.setFieldValByName("deleted",new Long(0),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
