
# 注解

> 注解是一种能被添加到java代码中的元数据，类、方法、变量、参数和包都可以用注解来修饰。注解对于它所修饰的代码并没有直接的影响。

> 注解又许多用法，其中有：
为编译器提供信息 - 注解能被编译器检测到错误或抑制警告。
编译时和部署时的处理 - 软件工具能处理注解信息从而生成代码，XML文件等等。
运行时的处理 - 有些注解在运行时能被检测到。

# 自定义注解

1. 定义注解——相当于定义标记
2. 配置注解——把标记打在需要用到的程序代码中
3. 解析注解——在编译期或运行时检测到标记，并进行特殊操作

## 定义

```java
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnnotationOne {
    String name();
    int age() default 22;
    int[] arr();
}
```

### 定义注解类型元素时需要注意如下几点：    

1. 访问修饰符必须为public，不写默认为public；
1. 该元素的类型只能是基本数据类型、String、Class、枚举类型、注解类型（体现了注解的嵌套效果）以及上述类型的一位数组；
1. 该元素的名称一般定义为名词，如果注解中只有一个元素，请把名字起为value（后面使用会带来便利操作）；
1. ()不是定义方法参数的地方，也不能在括号中定义任何参数，仅仅只是一个特殊的语法；
1. default代表默认值，值必须和第2点定义的类型一致；
1. 如果没有默认值，代表后续使用注解时必须给该类型元素赋值

### @Target

> @Target注解，是专门用来限定某个自定义注解能够被应用在哪些Java元素上面的。它使用一个枚举类型定义

```java
public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    /** 类，接口（包括注解类型）或枚举的声明 */
    TYPE,

    /** Field declaration (includes enum constants) */
    /** 属性的声明 */
    FIELD,

    /** Method declaration */
    /** 方法的声明 */
    METHOD,

    /** Formal parameter declaration */
    /** 方法形式参数声明 */
    PARAMETER,

    /** Constructor declaration */
    /** 构造方法的声明 */
    CONSTRUCTOR,

    /** Local variable declaration */
    /** 局部变量声明 */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
     /** 注解类型声明 */
    ANNOTATION_TYPE,

    /** Package declaration */
     /** 包的声明 */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE
}
```

### @Retention

> @Retention注解，翻译为持久力、保持力。即用来修饰自定义注解的生命力。
注解的生命周期有三个阶段：1、Java源文件阶段；2、编译到class文件阶段；3、运行期阶段。
同样使用了RetentionPolicy枚举类型定义了三个阶段：

```java
public enum RetentionPolicy {
    /**
     * Annotations are to be discarded by the compiler.
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     *
     * @see java.lang.reflect.AnnotatedElement
     */
    RUNTIME
}
```

详解一下：

- 如果一个注解被定义为RetentionPolicy.SOURCE，则它将被限定在Java源文件中，那么这个注解即不会参与编译也不会在运行期起任何作用，这个注解就和一个注释是一样的效果，只能被阅读Java文件的人看到；
- 如果一个注解被定义为RetentionPolicy.CLASS，则它将被编译到Class文件中，那么编译器可以在编译时根据注解做一些处理动作，但是运行时JVM（Java虚拟机）会忽略它，我们在运行期也不能读取到；
- 如果一个注解被定义为RetentionPolicy.RUNTIME，那么这个注解可以在运行期的加载阶段被加载到Class对象中。那么在程序运行阶段，我们可以通过反射得到这个注解，并通过判断是否有这个注解或这个注解中属性的值，从而执行不同的程序代码段。我们实际开发中的自定义注解几乎都是使用的RetentionPolicy.RUNTIME；
- 在默认的情况下，自定义注解是使用的RetentionPolicy.CLASS。

### 2.2.3 @Documented

> @Documented注解，是被用来指定自定义注解是否能随着被定义的java文件生成到JavaDoc文档当中。

###  @Inherited

> @Inherited注解，是指定某个自定义注解如果写在了父类的声明部分，那么子类的声明部分也能自动拥有该注解。
@Inherited注解只对那些@Target被定义为`ElementType.TYPE`的自定义注解起作用。



### 特殊语法

- 特殊语法一：

如果注解本身没有注解类型元素，那么在使用注解的时候可以省略()，直接写为：@注解名，它和标准语法@注解名()等效！
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface FirstAnnotation {
}
``` 

```java
//等效于@FirstAnnotation()
@FirstAnnotation
public class JavaBean{
	//省略实现部分
}
```

- 特殊语法二：

如果注解本本身只有一个注解类型元素，而且命名为value，那么在使用注解的时候可以直接使用：@注解名(注解值)，其等效于：@注解名(value = 注解值)

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface SecondAnnotation {
	String value();
}
```
 
```java
//等效于@ SecondAnnotation(value = "this is second annotation")
@SecondAnnotation("this is annotation")
public class JavaBean{
	//省略实现部分
}
```

- 特殊用法三：

如果注解中的某个注解类型元素是一个数组类型，在使用时又出现只需要填入一个值的情况，那么在使用注解时可以直接写为：@注解名(类型名 = 类型值)，它和标准写法：@注解名(类型名 = {类型值})等效！

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface ThirdAnnotation {
	String[] name();
}
```

```java
//等效于@ ThirdAnnotation(name = {"this is third annotation"})
@ ThirdAnnotation(name = "this is third annotation")
public class JavaBean{
	//省略实现部分
}
```
 
- 特殊用法四：

如果一个注解的@Target是定义为Element.PACKAGE，那么这个注解是配置在package-info.java中的，而不能直接在某个类的package代码上面配置。

————————————————
参考：https://blog.csdn.net/xsp_happyboy/article/details/80987484


## 使用

```java
public class MyStudent {

    //使用注解，如果无默认值的，必须赋值：有默认值的可以重新赋值
    @MyAnnotationOne(name = "yxy coding", arr = {23, 11, 23})
    public void study(int times) {
        for (int i = 0; i < times; i++) {
            log.info("Good Good Study, Day Day Up!");
        }
    }
}
```


### 解释

```java

public class MyTestAnnotation {
    public static void main(String[] args){
        try {
            //获取Student的Class对象
            Class stuClass = Class.forName("com.yxycoding.demo.myannotiation.normal.MyStudent");


            //说明一下，这里形参不能写成Integer.class，应写为int.class
            Method stuMethod = stuClass.getMethod("study",int.class);

            if(stuMethod.isAnnotationPresent(MyAnnotationOne.class)){
                System.out.println("Student类上配置了CherryAnnotation注解！");
                //获取该元素上指定类型的注解
                MyAnnotationOne cherryAnnotation = stuMethod.getAnnotation(MyAnnotationOne.class);
                System.out.println("name: " + cherryAnnotation.name() + ", age: " + cherryAnnotation.age()
                        + ", score: " + cherryAnnotation.arr()[0]);
            }else{
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
```


