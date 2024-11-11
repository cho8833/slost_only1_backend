package com.slost_only1.slost_only1.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.slost_only1.slost_only1.base.BaseEnum;
import io.micrometer.common.util.StringUtils;

public class BooleanExpressionUtil {

    public static BooleanExpression eq(NumberPath<Long> expression, Long data) {
        if (data != null) {
            return expression.eq(data);
        }
        return null;
    }

    public static BooleanExpression eq(StringPath expression, String data) {
        if (!StringUtils.isEmpty(data)) {
            return expression.eq(data);
        }
        return null;
    }
    public static BooleanExpression eq(EnumPath expression, BaseEnum data) {
        if (data != null) {
            return expression.eq(data);
        }
        return null;
    }
}
