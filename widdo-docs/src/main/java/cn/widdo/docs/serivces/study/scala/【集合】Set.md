`scala`集合之`Set`。参考：`WiddoSet`
> `scala set`是一个可迭代的，没有重复元素的集合。`Set`集合也是既有可变的，也有不可变的

 - **可变`Set`**
> ```
> val set: mutable.Set[Int] = collection.mutable.Set(1, 2, 1, 3)
> ```

添加元素。可以使用以下集中方法进行元素添加
```
+=
++=
add
```
删除元素。可以使用以下方法进行元素删除
```
-=
--=
remove
```

 - **不可变`Set`**
> ```
> val set: Set[Int] = collection.immutable.Set(1, 2, 3, 1)
> ```