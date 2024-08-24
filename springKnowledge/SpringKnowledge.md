## Spring IoC Container and Beans

Dependency injection (DI) is a specialized form of IoC, whereby objects define their dependencies (that is, the other objects they work with) only through:
1. constructor arguments, 
2. arguments to a factory method, or 
3. properties that are set on the object instance after it is constructed or returned from a factory method. 

The IoC container then injects those dependencies when it creates the bean. 
This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation 
or location of its dependencies by using direct construction of classes or a mechanism such as the Service Locator pattern

##### Beans and contexts
The `org.springframework.beans` and `org.springframework.context` packages are the basis for Spring Framework’s IoC container. 

The **BeanFactory interface provides an advanced configuration mechanism capable of managing any type of object**. 
ApplicationContext is a sub-interface of BeanFactory. It adds:
1. Easier integration with Spring’s AOP features
2. Message resource handling (for use in internationalization)
3. Event publication
4. Application-layer specific contexts such as the WebApplicationContext for use in web applications

In nutshell, the **BeanFactory provides the configuration framework and basic functionality**, 
and the **ApplicationContext adds more enterprise-specific functionality**.


![img.png](img.png)




