package org.jetbrains.plugins.scala.failed.typeInference

import org.jetbrains.plugins.scala.PerfCycleTests
import org.jetbrains.plugins.scala.base.ScalaLightCodeInsightFixtureTestAdapter
import org.junit.experimental.categories.Category

/**
  * @author Roman.Shein
  * @since 01.04.2016.
  */
@Category(Array(classOf[PerfCycleTests]))
class SetConformanceTest extends ScalaLightCodeInsightFixtureTestAdapter {
  def testSCL4941(): Unit = checkTextHasNoErrors(
    s"""
       |def f(collect: Iterable[Int]): Unit = {
       |  collect.zipWithIndex.foldLeft(mutable.LinkedHashMap.empty[Int, Set[Int]]) {
       |    case (m, (t1, _)) => m += (t1 -> {
       |      val s = m.getOrElse(t1, mutable.LinkedHashSet.empty)
       |      s
       |    })
       |  }
       |}
       |//true
    """.stripMargin)
}
