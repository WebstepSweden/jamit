package se.diversify.jamit.repository.impl.mongodb

import se.diversify.jamit.repository.BaseDao
import com.mongodb.casbah.Imports._
import com.novus.salat._
import dao.ModelCompanion
import se.diversify.jamit.domain.Item
import com.novus.salat.global._

trait BaseDaoMongodbImpl[T <: Item] extends BaseDao[T] {

  protected val modelCompanion: ModelCompanion[T, ObjectId]

  override def get(id: ObjectId): T = modelCompanion.findOneById(id) getOrElse (throw new IllegalArgumentException)

  override def get(id: Int): T = modelCompanion.findOne(MongoDBObject("id" -> id)) getOrElse(throw new IllegalArgumentException)

  override def update(item: T): T = {
    modelCompanion.update(MongoDBObject("_id" -> item._id), modelCompanion.toDBObject(item), false, false)
    get(item._id)
  }

  override def add(item: T): T = modelCompanion.insert(item) match {
    case Some(id) => get(id)
    case None => throw new IllegalArgumentException
  }

  override def delete(id: ObjectId) {
    modelCompanion.removeById(id)
  }

  override def delete(id: Int) {
    delete(get(id)._id)
  }

  override def getAll(): List[T] = modelCompanion.findAll.toList
}
