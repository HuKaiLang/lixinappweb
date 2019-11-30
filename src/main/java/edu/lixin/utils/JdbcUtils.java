package edu.lixin.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class JdbcUtils {
    private static DataSource dataSource;
    static{
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
