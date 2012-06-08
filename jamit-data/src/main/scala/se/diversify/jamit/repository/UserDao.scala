package se.diversify.jamit.repository

import impl.UserDaoImpl
import se.diversify.jamit.domain.User

/**
 * Defines user database operations
 */
abstract class UserDao extends BaseDao[User] {

  /**Get a user given an email address
   * @param email the user's email address
   * @return the user instance
   */
  def getByEmail(email: String): User
}

/**
 * Defines common user database operations
 */
object UserDao {

  /**
   * Retrieve the default UserDao
   */
  val defaultDao = new UserDaoImpl
}
