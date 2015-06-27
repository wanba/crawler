package com.qinziwanba.commons;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangzhiguo on 15/6/27.
 */
public class AppStats {
    private Properties properties = System.getProperties();
    private Map<String, String> env = System.getenv();
    private List<String> vmargs;
    private Map<String, Object> status;

    public AppStats() {
        RuntimeMXBean rbean = ManagementFactory.getRuntimeMXBean();
        this.vmargs = rbean.getInputArguments();
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<String, String> getEnv() {
        return this.env;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

    public List<String> getVmargs() {
        return this.vmargs;
    }

    public void setVmargs(List<String> vmargs) {
        this.vmargs = vmargs;
    }

    public Map<String, Object> getStatus() {
        return this.status;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }

    public String toString() {
        return this.properties + " \n" + this.env + " \n" + this.vmargs;
    }
}
