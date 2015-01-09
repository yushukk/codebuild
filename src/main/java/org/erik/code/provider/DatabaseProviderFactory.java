package org.erik.code.provider;

import org.apache.commons.lang.StringUtils;
import org.erik.code.provider.impl.MysqlProvider;
import org.erik.code.provider.impl.OracleProvider;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.exceptions.EasyCodeException;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class DatabaseProviderFactory {
    /**
     * 获取数据库操作对象
     *
     * @return
     */
    public static DatabaseProvider buildProvider() {

        String dialect = EasyCodeContext.getJdbcConfig("dialect");

        if (StringUtils.equalsIgnoreCase(dialect, "oracle")) {
            return new OracleProvider();
        } else if (StringUtils.equalsIgnoreCase(dialect, "mysql")) {
            return new MysqlProvider();
        }

        throw new EasyCodeException("没有找到对应的数据库操作类,dialect:" + dialect);
    }
}
