package com.sz.bewater.practice.interview.framework.SpringMVC;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerAdapter;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/3/13
 */
@RestController
public class MyController {
    //当我们访问controller时可以在 DispatcherServlet#doDispatcher方法的这两处打上断点调试
    // Determine handler for the current request.
//    mappedHandler = getHandler(processedRequest); 返回结果为 HandlerExecutionChain
//    包含 1.handler-处理器(com.sz.bewater.practice.interview.framework.SpringMVC.MyController.test)
    //2.InterceptorList 包含我们自定义拦截器 MyHandlerInterceptor
    //HandlerExecutionChain with [com.sz.bewater.practice.interview.framework.SpringMVC.MyController#test()] and 3 interceptors

    //第二处打断点的地方:
    // Determine handler adapter for the current request.
//    HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler()); //获取该处理器对应的处理器适配器
//在这里由于是@RequestMapping 方式的处理器 则对应的适配器为 RequestMappingHandlerAdapter


    /**
     // DispatcherServlet#doDispatcher() 的核心逻辑

     protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
     // 1. 获取请求的 HandlerExecutionChain（包含处理器和拦截器）
     HandlerExecutionChain mappedHandler = getHandler(processedRequest);

     // 2. 获取对应的 HandlerAdapter
     HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

     // 3. 执行拦截器的 preHandle() 方法
     if (!mappedHandler.applyPreHandle(processedRequest, response)) {
     return; // 若拦截器中断请求，直接返回
     }

     // 4. 通过 HandlerAdapter 调用处理器，返回 ModelAndView
     ModelAndView mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

     // 5. 执行拦截器的 postHandle() 方法（视图渲染前）
     mappedHandler.applyPostHandle(processedRequest, response, mv);

     // 6. 渲染视图并返回响应
     processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);

     // 7. 执行拦截器的 afterCompletion() 方法（请求完成后）
     mappedHandler.triggerAfterCompletion(processedRequest, response, null);
     }

     protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
     if (this.handlerMappings != null) {
     //遍历所有注册进 Spring 容器中的 HandlerMapping 知道找到能处理该请求的 HandlerMapping
     for (HandlerMapping mapping : this.handlerMappings) {
     HandlerExecutionChain handler = mapping.getHandler(request);
     if (handler != null) {
     return handler;
     }
     }
     }
     return null;
     }
     */


    /**
     * 当请求到达`DispatcherServlet`时，它会调用`getHandler`方法，该方法会遍历所有注册的`HandlerMapping`，
     * 直到找到能够处理当前请求的处理器。每个`HandlerMapping`会返回一个`HandlerExecutionChain`，
     * 其中包含了处理器和相关的拦截器列表
     * 然后调用'getHandlerAdapter'方法 传入 Handler 找到其对应的处理器适配器 HandlerAdapter
     * preHandler(有拦截器的话)
     * 适配器调用 handle 方法
     * postHandler(有拦截器的话)
     * 返回 ModelAndView
     * 视图解析器 渲染视图
     * afterCompletion(有拦截器的话)
     */

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/release",method = RequestMethod.GET)
    public String release(){
        return "release";
    }
}
