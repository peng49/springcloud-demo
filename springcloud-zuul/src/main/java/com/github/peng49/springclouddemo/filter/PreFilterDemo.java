package com.github.peng49.springclouddemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class PreFilterDemo extends ZuulFilter {
    /**
     * 在请求前pre或者后post执行
     *
     * @return String
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序，数字越小，表示越先执行
     *
     * @return int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启true表示开启
     *
     * @return bool
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return 任何 object 的值都表示继续执行
     * setSendZuulResponse(false)表示不再继续执行
     *
     * @return object
     * @throws ZuulException 异常
     */
    @Override
    public Object run() throws ZuulException {
        log.info(PreFilterDemo.class.getName());

        RequestContext requestContext = RequestContext.getCurrentContext();

        //request域
        HttpServletRequest request = requestContext.getRequest();

        log.info(request.getHeader("User-Agent"));

        return null;
    }
}
