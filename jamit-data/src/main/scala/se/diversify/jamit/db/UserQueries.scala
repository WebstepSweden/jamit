package se.diversify.jamit.db

import org.scalaquery.ql.{Query, SimpleFunction}

/**Defines database queries for the users database table */
object UserQueries {


  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession
  import se.diversify.jamit.domain.User

  /**Retrieve all users
   * @return all users in the database
   */
  def getAllUsers: List[User] = {
    val query = for (u <- Users) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      query.list.map(user => User(user._1, user._2, user._3, user._4, user._5))
    }
  }

  /**Retrieve a user given its id
   * @param id the user's id
   * @return the user
   */
  def getUser(id: Int): User = {
    val query = for (u <- Users if u.id === id) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      val user = query.first
      User(user._1, user._2, user._3, user._4, user._5)
    }
  }

  /**Retrieve a user given its email
   * @param email the user's email address
   * @return the user
   */
  def getUserByEmail(email: String): User = {
    val query = for (u <- Users if u.email == email) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      val user = query.first
      User(user._1, user._2, user._3, user._4, user._5)
    }
  }

  /**Insert a user in the database
   * @param user the use to insert
   * @return the new user
   */
  def insertUser(user: User): User = {
    DB.database withSession {
      Users.insert(
        (0, user.name, user.email, user.role, user.password)
      )
      val scopeIdentity = SimpleFunction.nullary[Int]("LAST_INSERT_ID")
      val newId = Query(scopeIdentity).first
      user.copy(id = newId)
    }
  }

  /**Update a user in the database
   * @param user the user to update
   * @return the updated user
   */
  def updateUser(user: User): User = {
    val query = for (u <- Users if u.id === user.id) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      query.update(user.id, user.name, user.email, user.role, user.password)
      user
    }
  }

  /**Delete a user from the database
   * @param id the id of the user to delete
   */
  def deleteUser(id: Int) {
    val query = for (u <- Users if u.id === id) yield (u)
    DB.database withSession {
      query.mutate(_.delete)
    }
  }

}
