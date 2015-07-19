package tutorial.webapp

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.JSConverters._

import org.scalajs.dom
import dom.document

import com.greencatsoft.d3.common._
import com.greencatsoft.d3.selection.ElementIterator

object TutorialApp extends JSApp {
  val d3 = com.greencatsoft.d3.svg.d3

  def main(): Unit = {
    val points = List(
      (10.0, 9.14),
      (8.0, 8.14),
      (13.0, 8.74),
      (9.0, 8.77),
      (11.0, 9.26),
      (14.0, 8.10),
      (6.0, 6.13),
      (4.0, 3.10),
      (12.0, 9.13),
      (7.0, 7.26),
      (5.0, 4.74))

    plot(points, title="Anscomb's Quartet, #2")
  }

  def plot(points: Iterable[(Double, Double)],
    xRange: Option[(Double, Double)] = None,
    yRange: Option[(Double, Double)] = None,
    width: Int = 400,
    height: Int = 300,
    title: String = ""): Unit = {
    val xrange = xRange.getOrElse {
      (0.0, points.map(_._1).max * 1.1)
    }
    val yrange = yRange.getOrElse {
      (0.0, points.map(_._2).max * 1.1)
    }
    val xwidth = xrange._2 - xrange._1
    val yheight = yrange._2 - yrange._1

    val viz = d3.select("body").append("svg")
    viz.attr("width", width)
      .attr("height", height)

    //viz.text(title.getOrElse("Title"))
    //  .select("#graph")

    val text = viz.selectAll("text")
      .data(points.toJSArray)
      .enter
      .append("text")
      .attr("x", 20)
      .attr("y", 20)

    text.text(title)

    viz.selectAll("circle.nodes")
      .data(points.map { case (x,y) => Point(x,y) }.toJSArray)
      .enter
      .append("svg:circle")
      .attr[Point]("cx", { (self: dom.svg.Element, d: UndefOr[Point], i: UndefOr[Int]) => d.get.x * width / xwidth })
      .attr[Point]("cy", { (self: dom.svg.Element, d: UndefOr[Point], i: UndefOr[Int]) => height - d.get.y * height / yheight })
      .attr("r", "3px")
      .attr("fill", "black")

    viz.append("line")
      .attr("x1", 0)
      .attr("y1", height)
      .attr("x2", width)
      .attr("y2", height)
      .attr("stroke-width", 2)
      .attr("stroke", "black")

    viz.append("line")
      .attr("x1", 0)
      .attr("y1", 0)
      .attr("x2", 0)
      .attr("y2", height)
      .attr("stroke-width", 2)
      .attr("stroke", "black")
  }

  def randomColor: String = "hsl(" + math.random * 360 + ",100%,50%)"

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExport
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
}
