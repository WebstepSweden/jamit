package se.diversify.jamit.domain

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoConnection

case class User(override val _id: ObjectId = new ObjectId, name: String, email: String, phone: String, role: String, password: String) extends Item(_id)

object User {
  def create(name: String, email: String, phone: String, role: String, password: String) = this(new ObjectId, name, email, phone, role, password)
}