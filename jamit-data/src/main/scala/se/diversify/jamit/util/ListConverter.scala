package se.diversify.jamit.util

class ListConverter

object ListConverter {

  import scala.collection.mutable.ArrayBuffer

  import scala.collection.JavaConversions._
  import java.util.{List => JList}

  def toJava[T](l: List[T]): JList[T] = ArrayBuffer(l: _*)

  def fromJava[T](l: JList[T]) = asList(l).toList
}
