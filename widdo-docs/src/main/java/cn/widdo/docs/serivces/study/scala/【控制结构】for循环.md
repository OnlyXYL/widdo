`scala`控制结构之for循环，参考：`ControlStructure`

 - 循环List集合

```
//list
val nums = Seq(1,2,3)

//循环打印，方法一：for
for (num <- nums) println(num)

//循环打印，方法二：foreach
nums.foreach(println)

```

 - 循环Map集合
```
//map
val map = Map(
    "张三" -> 18,
    "里斯" -> 20,
    "小红" -> 17
)

//循环打印，方法一：for
for ((name,age) <- map) println(s"我是：${name}，今年 ${age} 岁！")

//循环打印，方法二：foreach
map.foreach {
    case (namge,age) => println(s"我是：${namge}，今年 ${age} 岁！")
}


```