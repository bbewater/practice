package com.sz.bewater.practice.interview.framework.SpringMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/13
 */
@Component
public class MyHandlerInterceptor implements HandlerInterceptor {
//    @Autowired
//    private List<HandlerMapping> handlerMappings;
//    @Autowired
//    private List<HandlerAdapter> handlerAdapters;
//
//    @PostConstruct
//    public void init(){
//        handlerMappings.stream().forEach(handlerMapping -> {
//            System.out.println("注入的 handlerMapping = " + handlerMapping);
//        });
//        handlerAdapters.stream().forEach(handlerAdapter -> {
//            System.out.println("注入的 handlerAdapter = " + handlerAdapter);
//        });
//    }

    /**
     * 在处理器（Controller 方法）执行前触发。
     * 典型应用：权限校验、请求参数预处理、日志记录。
     * 返回值：true（继续流程）或 false（中断请求，直接返回响应）。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 在处理器执行后、视图渲染前触发。
     * 典型应用：向 Model 添加全局数据、修改响应头。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * 在整个请求完成后触发（包括视图渲染完成）。
     * 典型应用：资源清理、性能统计（记录请求耗时）。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * [请求到达 DispatcherServlet]
     *     │
     *     ▼
     * 1. HandlerMapping 返回 HandlerExecutionChain（包含 Handler + 拦截器列表）
     *     │
     *     ▼
     * 2. 按顺序执行所有拦截器的 preHandle() 方法
     *     │
     *     ▼
     * 3. 如果所有 preHandle() 返回 true → 调用 HandlerAdapter 执行处理器
     *     │
     *     ▼
     * 4. 处理器执行完毕 → 按逆序执行拦截器的 postHandle() 方法
     *     │
     *     ▼
     * 5. 视图渲染完成后 → 按逆序执行拦截器的 afterCompletion() 方法
     */
}
