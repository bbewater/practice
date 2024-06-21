BeanFactory是spring框架的顶层接口 是一个容器 用来管理交由spring管理的所有的bean

FactoryBean 是一个创建复杂bean的工厂 本身也是一个特殊的bean(实现FactoryBean接口 重写getObject 通过getObject创建bean)

所以spring创建bean的方式有: 1.通过反射调用无参构造 2.工厂方法  3.通过FactoryBean创建