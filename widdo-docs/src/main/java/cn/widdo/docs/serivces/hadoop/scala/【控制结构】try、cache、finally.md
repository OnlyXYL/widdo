`scala`控制结构 `try/cache/finally`，参考：`ControlStructure`

```
try {
  throw new Exception("error message.")
} catch {
  case e: Exception => println(s"异常信息：${e.getMessage}")
} finally {
  println("这里是 scala 的 异常 finally代码块.")
}
```