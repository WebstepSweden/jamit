package se.diversify.jamit.repository.impl.mongodb

import com.mongodb.casbah.Imports._
import se.diversify.jamit.domain.User
import se.diversify.jamit.repository.UserDao

class UserDaoMongodbImpl extends UserDao with BaseDaoMongodbImpl[User] {

  override val modelCompanion = User

  override def getByEmail(email: String): User = modelCompanion.findOne(MongoDBObject("email" -> email)).getOrElse(throw new IllegalArgumentException)
}