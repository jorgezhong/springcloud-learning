package com.jorge.apigateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/2/22 15:09.
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {

        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器优先级：order越小，过滤器执行优先级越大
     * 查看FilterConstants看优先级范围
     * @return
     */
    @Override
    public int filterOrder() {
        //设置该过滤器在编码之前
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
    }

    /**
     * 定义过滤条件
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //RequestContext对象共享在整个过滤器链当中，可从中获取请求数据
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String requestURI = request.getRequestURI();

        if (requestURI.contains("order")) {
            return true;
        }

        return false;
    }

    /**
     * 定义业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        System.out.println("成功拦截:" + request.getRequestURI());


        //简单的鉴权逻辑
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        if (StringUtils.isBlank(token)) {
            //没有token：未授权，不往走了
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
