`scala`的`match`表达式，参考：`ControlStructure`

> 主要包括两种用法：

 - **模式匹配**

> ```
> val i = true 
> 
> i match {
>   case true => "you said true"
>   case false => "you said false"
>   case _ => "Inviald input"
> }
> ```

 - **`match` 作为方法体**
> ```
> def convertBooleanToStringMessage(bool: Boolean): Unit = bool match {
>   
>   case true => println("you said true")
>   case fasle => printlm("you said true")
>   case _ => println("Invalid input")
> }
> ```
