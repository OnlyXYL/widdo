package cn.widdo.cache.configuration.redisson;

import java.util.concurrent.TimeUnit;

/**
 * @author XYL
 * @date 2022/06/22 11:19
 * @since 263.1.1.0
 */
public interface Locker {

	/**
	 * 获取锁，如果锁不可用，则当前线程处于休眠状态，直到获得锁为止.
	 * @param lockKey key
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	void lock(String lockKey);

	/**
	 * 释放锁.
	 * @param lockKey key
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	void unlock(String lockKey);

	/**
	 * 获取锁,如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。如果获取到锁后，执行结束后解锁或达到超时时间后会自动释放锁.
	 * @param lockKey lockKey
	 * @param timeout timeout
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	void lock(String lockKey, int timeout);

	/**
	 * 获取锁,如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。如果获取到锁后，执行结束后解锁或达到超时时间后会自动释放锁.
	 * @param lockKey lockKey
	 * @param unit unit
	 * @param timeout timeout
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	void lock(String lockKey, TimeUnit unit, int timeout);

	/**
	 * 尝试获取锁，获取到立即返回true,未获取到立即返回false.
	 * @param lockKey lockKey
	 * @return boolean
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	boolean tryLock(String lockKey);

	/**
	 * 尝试获取锁，在等待时间内获取到锁则返回true,否则返回false,如果获取到锁，则要么执行完后程序释放锁， 要么在给定的超时时间leaseTime后释放锁.
	 * @param lockKey key
	 * @param waitTime 等待时间
	 * @param leaseTime 释放时间
	 * @param unit 时间单位
	 * @return boolean
	 * @throws InterruptedException 异常
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;

	/**
	 * 锁是否被任意一个线程锁持有.
	 * @param lockKey key
	 * @return boolean
	 * @author XYL
	 * @className cn.widdo.configure.redisson.Locker
	 * @date 2022/07/05 17:42
	 **/
	boolean isLocked(String lockKey);

}
