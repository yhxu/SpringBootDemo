package com.xuyh.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WatchCallBack implements Watcher, AsyncCallback.StringCallback, AsyncCallback.Children2Callback, AsyncCallback.StatCallback {
    private final static String CREATE_LOCK = "createLock";
    private final static String GET_CHILDREN = "getChildren";
    private final static String LOCK_EXISTS = "lockExists";
    private final static String LOCK_PATH = "/lock";
    private final static String PATH = "/";

    ZooKeeper zooKeeperClient;
    // 创建锁的维度，此处以线程为例，实际业务中可能为具体业务逻辑，如：仓库编码+四级地址计算运力锁
    String lockObject;
    CountDownLatch cc = new CountDownLatch(1);
    String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Object getLockObject() {
        return lockObject;
    }

    public void setLockObject(String lockObject) {
        this.lockObject = lockObject;
    }

    public ZooKeeper getZooKeeperClient() {
        return zooKeeperClient;
    }

    public void setZooKeeperClient(ZooKeeper zooKeeperClient) {
        this.zooKeeperClient = zooKeeperClient;
    }

    /**
     * 锁方法
     */
    public void tryLock(){
        try {
            // 打印线程信息
            System.out.println(lockObject + "  create...");
            // 上锁方法，创建临时序列节点，数据为线程名称，监控为AsyncCallback.StringCallback
            zooKeeperClient.create(LOCK_PATH, lockObject.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,this, CREATE_LOCK);
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock(){
        try {
            zooKeeperClient.delete(pathName,-1);
            System.out.println(lockObject + " over work...");
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        //如果第一个哥们，那个锁释放了，其实只有第二个收到了回调事件！！
        //如果，不是第一个哥们，某一个，挂了，也能造成他后边的收到这个通知，从而让他后边那个跟去watch挂掉这个哥们前边的。。。
        switch (event.getType()) {
            case None:
            case NodeCreated:
            case NodeDataChanged:
            case NodeChildrenChanged:
                break;
            case NodeDeleted:
                // 监控到节点删除后，调用getChildren继续执行下一个锁
                zooKeeperClient.getChildren(PATH,false,this, GET_CHILDREN);
                break;
        }
    }

    // AsyncCallback.StringCallback create 方法中的回调
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        // 创建成功name不为空
        if(name != null ){
            System.out.println(lockObject +"  create node : " +  name );
            // 此处的pathName是带有“/”的，因此在下面的处理中要注意这点
            pathName =  name ;
            // 获取当前节点父节点下的所有子节点，监控为AsyncCallback.Children2Callback
            zooKeeperClient.getChildren(PATH,false,this, GET_CHILDREN);
        }
    }

    //getChildren  call back
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {

        // 给节点升序排序
        Collections.sort(children);
        // 回调中获取到的pathName带有“/”，children中的节点名称不带有斜线，因此要截取
        int i = children.indexOf(pathName.substring(pathName.indexOf(PATH)+1));

        //判断当前节点是不是第一个
        if(i == 0){
            //是，获取到锁，即count down
            System.out.println(lockObject +" i am first...");
            try {
                // 设置数据，增加延迟性，防止死循环
                zooKeeperClient.setData(PATH, lockObject.getBytes(),-1);
                cc.countDown();
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            //否 未获取到锁，监控前一个节点的删除状态，当前一个节点删除时，继续执行getChildren
            // watcher 为当前监控，call back为 AsyncCallback.StatCallback
            zooKeeperClient.exists(PATH + children.get(i-1),this,this,LOCK_EXISTS);
        }
    }
    /**
     * 监控 exists call back，做一些异常监控等操作
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {

    }
}
