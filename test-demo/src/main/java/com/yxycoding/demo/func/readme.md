# 方法的参数是 函数 

## jdk8之后的 谓词 来实现

> 传递方法 
传递lambda

## jdk8之前实现 

> 定义接口，接口做为参数。

- 对于不同的业务，实现不同的类，以类的实例做为参数传递。

- 如果实现比较简单明了，可以不用写实现类，如下：

```java
    /**
     * 在jdk8之前，没有 lambda的写法，可以如下写
     */
    List<AppleT> list1 = appleFilter.filterApples(list, new Predicate<AppleT>() {
        @Override
        public boolean test(AppleT a) {
            return a.getWeight() > 150;
        }
    });
```