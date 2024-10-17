package com.shard.config;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    private static final String defaultKey = "shard3";
    public static void setDataSourceKey(String dbType) {
        System.out.println("dbType " + dbType);
        contextHolder.set(dbType);
    }
    public static String getDataSourceKey() {
        System.out.println("get ds key "+ contextHolder.get());
        String dsKey = contextHolder.get() == null ? defaultKey : contextHolder.get();
        System.out.println("dsKey " + dsKey);
        return dsKey;
    }
    public static void clear() {
        contextHolder.remove();
    }
}
