package se.diversify.jamit.repository.impl.mongodb

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.domain.User
import se.diversify.jamit.repository.UserDao

class UserDaoMongodbImpl extends UserDao with BaseDaoMongodbImpl[User] {

  override val dao = UserDaoMongodbImpl

  override def itemAsDBObject(user: User): DBObject = grater[User].asDBObject(user)

  override def getByEmail(email: String): User = dao.findOne(MongoDBObject("email" -> email)).get
}

import Constants._

object UserDaoMongodbImpl extends SalatDAO[User, ObjectId](collection = MongoConnection()(databaseName)(userCollection))
