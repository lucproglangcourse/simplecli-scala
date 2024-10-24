object SalesTaxFunctional:

  type Result = (String, Int)

  def f(lines: Iterator[String]): Iterator[Result] =
    lines
      .scanLeft("dummy", 0):
        case ((prevItem, acc), nextItem) =>
          (nextItem, acc + nextItem.length)
      .drop(1)

  def main(args: Array[String]): Unit =
    val lines = scala.io.Source.stdin.getLines()
    val result = f(lines)
    result
      // terminate on I/O error such as SIGPIPE
      .takeWhile: _ =>
        !scala.sys.process.stdout.checkError()
      .foreach: r =>
        println(r)

end SalesTaxFunctional
