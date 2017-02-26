package org.jnosql.artemis.demo.se.column;


import org.jnosql.artemis.column.ColumnRepositoryInterceptor;

import javax.interceptor.Interceptor;

@Interceptor
@ColumnRepositoryInterceptor
public class PersonInterceptor {
}
