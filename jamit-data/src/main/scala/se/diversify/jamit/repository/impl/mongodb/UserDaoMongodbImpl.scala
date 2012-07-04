package se.diversify.jamit.repository.impl.mongodb

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.domain.User
import se.diversify.jamit.repository.UserDao

class UserDaoMongodbImpl extends UserDao {

  import UserDaoMongodbImpl.{update => _update, _}

  override def get(id: ObjectId): User = findOne(MongoDBObject("_id" -> id)) match {
    case Some(user) => user
    case None => null
  }

  override def update(user: User): User = {
    _update(MongoDBObject("_id" -> user._id), grater[User].asDBObject(user))
    get(user._id)
  }

  override def add(user: User): User = {
    val id = insert(user)
    get(id.get)
  }

  override def delete(id: ObjectId) {removeById(id)}

  override def getAll(): List[User] = find(ref = MongoDBObject()).toList

  override def getByEmail(email: String): User = findOne(MongoDBObject("email" -> email)).get
}

/**
 * Defines common user database operations
 */
object UserDaoMongodbImpl extends SalatDAO[User, ObjectId](collection = MongoConnection()("jamit_db")("user_coll"))
