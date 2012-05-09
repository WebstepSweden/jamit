package se.diversify.jamit.repository

import se.diversify.jamit.domain.User

abstract class UserDao {
  def get(id: Int): User
  def update(user: User): User
  def add(user: User): User
  def delete(id: Int)
  def getAll(): Map[Int, User]
}

object UserDao {
  val defaultDao = new UserDaoImpl
}
