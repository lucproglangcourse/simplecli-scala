object SalexTaxTestable:

  type Result = (String, Int)

  trait OutputObserver:
    def update(result: Result): Unit

  def f(lines: Iterator[String], output: OutputObserver): Unit =
    var line = "dummy"
    var length = 0
    while lines.hasNext do
      val next = lines.next()
      line = next
      length = length + next.length
      output.update((line, length))
      // terminate on I/O error such as SIGPIPE
      if scala.sys.process.stdout.checkError() then
        sys.exit(1)

  def main(args: Array[String]): Unit =
    val lines = scala.io.Source.stdin.getLines()
    object outputToStdout extends OutputObserver:
      override def update(result: Result): Unit = println(result)
    f(lines, outputToStdout)

end SalexTaxTestable
