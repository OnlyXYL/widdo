package cn.widdo.study.redis.controller;

import cn.widdo.cache.utils.redisson.LockerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * redisson controller
 *
 * @author XYL
 * @version 1.0
 * @date 2022/07/05 18:21
 */
@Slf4j
@RequestMapping(value = "/redisson")
@RestController
public class RedissonController {

    static final String KEY = "LOCK_KEY";

    /**
     * 测试加锁
     *
     * @param
     * @return java.lang.Object
     * @throws
     * @author XYL
     * @className cn.widdo.redis.controller.RedissonController
     * @date 2022/07/05 18:34
     **/
    @GetMapping("/test")
    public Object test1() {
        //加锁
        LockerUtils.lock(KEY);
        try {
            System.out.println(" 处理业务。。。");
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            //异常处理
        } finally {
            //释放锁
            LockerUtils.unlock(KEY);
        }

        return "SUCCESS";
    }

    /**
     * 加锁之前，判断是否已经有锁
     *
     * @param
     * @return java.lang.Object
     * @throws
     * @author XYL
     * @className cn.widdo.redis.controller.RedissonController
     * @date 2022/07/05 18:39
     **/
    @GetMapping(value = "/test2")
    public Object test2() {

        //校验是否有锁
        final boolean locked = LockerUtils.isLocked(KEY);

        if (!locked) {
            try {
                //加锁
                LockerUtils.lock(KEY);

                log.info("【测试分布式锁】处理业务......");
            } catch (Exception e) {
                //处理异常
                log.error("【测试分布式锁】处理业务，结果：【Error】......");
            } finally {
                //释放锁
                LockerUtils.unlock(KEY);
            }

            return "Success";

        } else {
            return "Failed";
        }
    }
}
