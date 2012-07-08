package se.diversify.jamit.repository.impl.mongodb

import se.diversify.jamit.repository.BaseDao
import com.novus.salat.dao.SalatDAO
import com.mongodb.casbah.Imports._
import com.novus.salat._
import se.diversify.jamit.domain.Item

trait BaseDaoMongodbImpl[T <: Item] extends BaseDao[T] {

  val dao: SalatDAO[T, ObjectId]

  override def get(id: ObjectId): T = dao.findOne(MongoDBObject("_id" -> id)) match {
    case Some(item) => item
    case None => null.asInstanceOf[T]
  }

  override def update(item: T): T = {
    dao.update(MongoDBObject("_id" -> item._id), itemAsDBObject(item))
    get(item._id)
  }

  def itemAsDBObject(item: T): DBObject

  override def add(item: T): T = {
    val id = dao.insert(item)
    get(id.get)
  }

  override def delete(id: ObjectId) {dao.removeById(id)}

  override def getAll(): List[T] = dao.find(ref = MongoDBObject()).toList
}
