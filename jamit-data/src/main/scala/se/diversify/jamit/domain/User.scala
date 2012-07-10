package se.diversify.jamit.domain

import org.bson.types.ObjectId

case class User(override val _id: ObjectId = new ObjectId, name: String, email: String, phone: String, role: String, password: String) extends Item(_id)

object User {
  def create(name: String, email: String, phone: String, role: String, password: String) = this(new ObjectId, name, email, phone, role, password)
}