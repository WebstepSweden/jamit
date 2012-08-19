package se.diversify.jamit.domain

import org.bson.types.ObjectId
import com.novus.salat.annotations.raw.Key
import com.mongodb.casbah.MongoConnection
import se.diversify.jamit.repository.impl.mongodb.Constants._
import com.novus.salat.dao._
import com.novus.salat.global._

case class User(@Key("_id") override val _id: ObjectId = new ObjectId, id: Int, name: String, email: String, phone: String, role: String, password: String) extends Item

object User extends ModelCompanion[User, ObjectId] {

  val dao = new SalatDAO[User, ObjectId](collection = MongoConnection()(databaseName)(userCollection)) {}

  import Item._
  def create(name: String, email: String, phone: String, role: String, password: String) = this(new ObjectId, nextId(classOf[User]), name, email, phone, role, password)
}