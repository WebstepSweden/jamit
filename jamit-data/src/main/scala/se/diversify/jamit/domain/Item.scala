package se.diversify.jamit.domain

import org.bson.types.ObjectId
import com.novus.salat.annotations.raw.{Salat, Key}

@Salat
trait Item {
  val _id: ObjectId
  val id: Int
}

object Item {
  val idMap = collection.mutable.Map[Class[_], Int]()

  def nextId(clazz: Class[_]): Int = idMap.get(clazz) match {
    case Some(i) => {
      idMap(clazz) = i + 1
      i
    }
    case None => {
      idMap += (clazz -> 0)
      0
    }
  }
}