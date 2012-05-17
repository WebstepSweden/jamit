package se.diversify.jamit.repository

import se.diversify.jamit.domain.User
import se.diversify.jamit.db._

/**Implementation of UserDao */
class UserDaoImpl extends UserDao {

  override def get(id: Int): User = UserQueries.getUser(id)

  override def getByEmail(email: String): User = UserQueries.getUserByEmail(email)

  override def update(user: User): User = UserQueries.updateUser(user)

  override def add(user: User): User = UserQueries.insertUser(user)

  override def delete(id: Int) {
    UserQueries.deleteUser(id)
  }

  override def getAll(): List[User] = UserQueries.getAllUsers

}
