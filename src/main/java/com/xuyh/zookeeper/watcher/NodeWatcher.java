package com.xuyh.zookeeper.watcher;

import com.xuyh.zookeeper.provider.ZooKeeperProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>className: NodeWatcher.java </p>
 * <p>Copyright: Copyright (C) 2006 </p>
 * <p>Company: 青牛技术股份有限公司 </p>
 * <p>
 * Description: 节点监控
 * </p>
 * <p>Author: cleve </p>
 * <p>CreateDate: 2024/3/12 10:31 </p>
 */
@Component
@Slf4j
public class NodeWatcher implements Watcher {
    @Resource(name = "zooKeeperProvider")
    private ZooKeeperProvider zooKeeperProvider;

    @Override
    public void process(WatchedEvent event) {
        if(zooKeeperProvider.serverEmpty()){
            zooKeeperProvider.register();
            log.info("server empty start slave server!");
        } else {
            List<String> serverList = zooKeeperProvider.getServerList();
            log.info(serverList.toString());
        }
    }
}
