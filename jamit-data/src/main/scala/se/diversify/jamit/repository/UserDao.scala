package se.diversify.jamit.repository

import se.diversify.jamit.domain.User

/**
 * Defines user database operations
 */
abstract class UserDao {
  /**
   * Get a user from the database given an id
   * @param id the user id
   * @return the user instance
   */
  def get(id: Int): User

  /**
   * Update user information
   * @param user the user to update
   * @return the updated user
   */
  def update(user: User): User

  /**
   * Add a new user in the database
   * @param user the user to add
   * @return the newly added user
   */
  def add(user: User): User

  /**
   * Remove a user from the database given its id
   * @param id the user id
   */
  def delete(id: Int)

  /**
   * Retrieve all users in the database
   * @return all the users in the database
   */
  def getAll(): List[User]
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
