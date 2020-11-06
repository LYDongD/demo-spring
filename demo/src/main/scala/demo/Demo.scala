package demo

/**
 * object 仅包含static方法和属性，可以直接引用
 * 包含main方法，作为程序的入口
 */
object Demo {

  def main(args: Array[String]): Unit = {
    var a = 10
    a = 5
    println(a)

    if (a > 7) println("big")
    else println("small")


    var i = 0
    for (i <- 1 to 10) {
      print(i)
    }
  }


}