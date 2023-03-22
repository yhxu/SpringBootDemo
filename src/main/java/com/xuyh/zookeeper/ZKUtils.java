package com.xuyh.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class ZKUtils {

    private static ZooKeeper zk;

    private static String address = "10.112.68.54:3181,10.112.76.31:3181,10.112.76.32:3181/testLock";
    // 使用内部类监控zk启动状态
    private static DefaultWatch watch = new DefaultWatch();
    // 计数，用来监控zk启动情况
    private static CountDownLatch init  =  new CountDownLatch(1);
    public static ZooKeeper getZK(){

        try {
            zk = new ZooKeeper(address,1000, watch);
            watch.setCc(init);
            init.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }

    public static WatchCallBack tryLock(ZooKeeper zooKeeper, String lockObject){
        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setZooKeeperClient(zooKeeper == null ? getZK() : zooKeeper);
        watchCallBack.setLockObject(lockObject);
        watchCallBack.tryLock();
        return watchCallBack;
    }

    public static void unLock(WatchCallBack watchCallBack){
        watchCallBack.unLock();
    }

    /**
     * 内部类，用来监控zk启动状态
     */
    static class DefaultWatch implements Watcher {

        CountDownLatch cc ;

        public void setCc(CountDownLatch cc) {
            this.cc = cc;
        }

        @Override
        public void process(WatchedEvent event) {

            System.out.println(event.toString());

            switch (event.getState()) {
                case Unknown:
                case NoSyncConnected:
                case Disconnected:
                case AuthFailed:
                case ConnectedReadOnly:
                case SaslAuthenticated:
                case Expired:
                    break;
                case SyncConnected:
                    // 链接成功后count down
                    cc.countDown();
                    break;
            }
        }
    }
}
