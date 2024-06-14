package cn.widdo.cache.utils.redisson;

import cn.widdo.cache.configuration.redisson.Locker;

import java.util.concurrent.TimeUnit;

/**
 * redis锁工具类.
 *
 * @author XYL
 * @date 2022/07/05 17:37
 * @since 263.1.1.0
 */
public class LockerUtils {

	protected LockerUtils() {
		throw new UnsupportedOperationException(LockerUtils.class.getName() + " 不能被实例化");
	}

	/**
	 * 锁.
	 */
	private static Locker locker;

	/**
	 * 设置工具类使用的locker.
	 * @param locker 锁对象
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:43
	 **/
	public static void setLocker(Locker locker) {
		LockerUtils.locker = locker;
	}

	/**
	 * 获取锁.
	 * @param lockKey lockKey
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:43
	 **/
	public static void lock(String lockKey) {
		locker.lock(lockKey);
	}

	/**
	 * 释放锁.
	 * @param lockKey lockKey
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:43
	 **/
	public static void unlock(String lockKey) {
		locker.unlock(lockKey);
	}

	/**
	 * 获取锁，超时释放.
	 * @param lockKey lockKey
	 * @param timeout 超时时间
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:43
	 **/
	public static void lock(String lockKey, int timeout) {
		locker.lock(lockKey, timeout);
	}

	/**
	 * 获取锁，超时释放，指定时间单位.
	 * @param lockKey lockKey
	 * @param unit 时间单位
	 * @param timeout 超时时间
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:43
	 **/
	public static void lock(String lockKey, TimeUnit unit, int timeout) {
		locker.lock(lockKey, unit, timeout);
	}

	/**
	 * 尝试获取锁，获取到立即返回true,获取失败立即返回false.
	 * <p>
	 * todo:该方法获取到锁之后，释放有问题。判断是否有锁，使用isLocked。后面调查
	 * @param lockKey lockKey
	 * @return boolean
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:43
	 **/
	public static boolean tryLock(String lockKey) {
		return locker.tryLock(lockKey);
	}

	/**
	 * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放.
	 * @param lockKey lockKey
	 * @param waitTime 等待时间
	 * @param leaseTime 施放时间
	 * @param unit 时间单位
	 * @return boolean
	 * @throws InterruptedException 异常
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:44
	 **/
	public static boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit)
			throws InterruptedException {
		return locker.tryLock(lockKey, waitTime, leaseTime, unit);
	}

	/**
	 * 锁释放被任意一个线程持有.
	 * @param lockKey lockKey
	 * @return boolean
	 * @author XYL
	 * @className cn.widdo.utils.redisson.LockerUtils
	 * @date 2022/07/05 17:44
	 **/
	public static boolean isLocked(String lockKey) {
		return locker.isLocked(lockKey);
	}

}
