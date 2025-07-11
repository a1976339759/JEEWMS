package org.jeecgframework.web.system.manager;

import java.util.Comparator;

import org.jeecgframework.web.system.pojo.base.Client;

/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
public class ClientSort implements Comparator<Client> {


    @Override
    public int compare(Client prev, Client now) {
        return (int) (now.getLogindatetime().getTime() - prev.getLogindatetime().getTime());
    }

}
