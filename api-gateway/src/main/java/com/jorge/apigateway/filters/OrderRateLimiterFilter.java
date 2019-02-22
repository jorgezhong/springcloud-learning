package com.jorge.apigateway.filters;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Project <springcloud-learning>
 * Created by jorgezhong on 2019/2/22 16:43.
 */
@Component
public class OrderRateLimiterFilter extends ZuulFilter {

    //设置一个令牌桶，速率是每秒可以产生1000个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000.0);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //优先级最大，在servlet检测前执行
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String requestURI = request.getRequestURI();

        if (requestURI.contains("order")) {
            return true;
        }

        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();

        //非阻塞式的获取一个令牌
        boolean limit = !RATE_LIMITER.tryAcquire();
        if (limit) {
            //限流逻辑
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }

        return null;
    }
}
