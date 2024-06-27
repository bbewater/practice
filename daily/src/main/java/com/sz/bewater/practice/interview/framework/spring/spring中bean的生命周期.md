Spring Bean生命周期详细步骤
1.通过BeanDefinition获得Bean的className，通过反射获得Class对象，然后通过Class对象获得构造方法，通过构造方法实例化对象

Spring首先会从配置文件或注解中获取Bean的BeanDefinition，其中包含Bean的配置信息，包括Bean的className。
通过反射机制，Spring会加载Bean的Class对象，并找到合适的构造方法来实例化Bean对象。
// 示例代码
Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
Constructor<?> constructor = beanClass.getDeclaredConstructor();
Object beanInstance = constructor.newInstance();


2.依赖注入属性（@Autowired、@Value）
Spring会解析Bean的属性和依赖关系，根据注解（如@Autowired、@Value）或XML配置进行依赖注入。
// 通过反射注入属性
Field field = beanClass.getDeclaredField("someField");
field.setAccessible(true);
field.set(beanInstance, someValue);

3.假如Bean实现了Aware接口，还需要调用相关Aware接口方法
如果Bean实现了某些Aware接口（如BeanNameAware、BeanFactoryAware、ApplicationContextAware），Spring会调用相应的方法进行注入。
if (beanInstance instanceof BeanNameAware) {
    ((BeanNameAware) beanInstance).setBeanName(beanName);
}
if (beanInstance instanceof BeanFactoryAware) {
    ((BeanFactoryAware) beanInstance).setBeanFactory(beanFactory);
}
if (beanInstance instanceof ApplicationContextAware) {
    ((ApplicationContextAware) beanInstance).setApplicationContext(applicationContext);
}

4.初始化之前调用BeanPostProcessor中的postProcessBeforeInitialization方法
在Bean初始化之前，Spring会调用所有注册的BeanPostProcessor的postProcessBeforeInitialization方法，允许对Bean进行进一步的处理。
for (BeanPostProcessor processor : beanPostProcessors) {
    beanInstance = processor.postProcessBeforeInitialization(beanInstance, beanName);
}

5.初始化（@PostConstruct、InitializingBean的afterPropertiesSet方法、自定义的init方法）
Spring会依次执行以下初始化方法：
使用@PostConstruct注解的方法。
实现了InitializingBean接口的afterPropertiesSet方法。
在Bean配置中指定的自定义初始化方法。
// 调用 @PostConstruct 方法
Method postConstructMethod = beanClass.getMethod("init");
postConstructMethod.invoke(beanInstance);
// 调用 InitializingBean 接口的 afterPropertiesSet 方法
if (beanInstance instanceof InitializingBean) {
    ((InitializingBean) beanInstance).afterPropertiesSet();
}
// 调用自定义初始化方法
Method customInitMethod = beanClass.getMethod("customInit");
customInitMethod.invoke(beanInstance);
初始化后调用BeanPostProcessor中的postProcessAfterInitialization方法（AOP）

6.在Bean初始化之后，Spring会调用所有注册的BeanPostProcessor的postProcessAfterInitialization方法。这一步常用于AOP代理的创建。
for (BeanPostProcessor processor : beanPostProcessors) {
    beanInstance = processor.postProcessAfterInitialization(beanInstance, beanName);
}

7.Bean创建完成
经过上述步骤后，Bean就完全创建并准备好使用了。
// Bean准备就绪，可以使用
MyBean myBean = (MyBean) beanInstance;

8.销毁Bean
当Spring容器关闭或Bean被移除时，会执行销毁步骤：
使用@PreDestroy注解的方法。
实现了DisposableBean接口的destroy方法。
在Bean配置中指定的自定义销毁方法。
// 调用 @PreDestroy 方法
Method preDestroyMethod = beanClass.getMethod("destroy");
preDestroyMethod.invoke(beanInstance);

// 调用 DisposableBean 接口的 destroy 方法
if (beanInstance instanceof DisposableBean) {
    ((DisposableBean) beanInstance).destroy();
}

// 调用自定义销毁方法
Method customDestroyMethod = beanClass.getMethod("customDestroy");
customDestroyMethod.invoke(beanInstance);