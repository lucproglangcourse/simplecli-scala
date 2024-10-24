import mainargs.{main, arg, ParserForMethods, Flag}

object SalesTaxImperative:

  // external entry point into Scala application
  def main(args: Array[String]): Unit =
    ParserForMethods(this).runOrExit(args.toIndexedSeq)
    ()

  // internal main method with arguments annotated for parsing
  @main
  def run(@arg(short = 't', doc = "sales tax") salesTax: Double = 0.1025): Unit =
    var runningTotal = 0.0
    val lines = scala.io.Source.stdin.getLines()
    while lines.hasNext do
      val next = lines.next()
      // TODO regex
      val tokens = next.split(',')
      val (unitPrice, description) = (tokens.head.toDouble, tokens.tail.mkString(","))
      val afterTaxPrice = (1 + salesTax) * unitPrice
      runningTotal += afterTaxPrice
      println(s"Processed $description @ $afterTaxPrice total = $runningTotal")
      // terminate on I/O error such as SIGPIPE
      if scala.sys.process.stdout.checkError() then
        sys.exit(1)

end SalesTaxImperative
