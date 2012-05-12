package se.diversify.jamit.db

import org.scalaquery.ql.{Query, SimpleFunction}


object UserQueries {

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession
  import se.diversify.jamit.domain.User

  def getAllUsers: List[User] = {
    val query = for (u <- Users) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      query.list.map(user => User(user._1, user._2, user._3, user._4, user._5))
    }
  }

  def getUser(id: Int): User = {
    val query = for (u <- Users if u.id === id) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      val user = query.first
      User(user._1, user._2, user._3, user._4, user._5)
    }
  }

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

  def updateUser(user: User): User = {
    val query = for (u <- Users if u.id === user.id) yield (u.id ~ u.name ~ u.email ~ u.role ~ u.password)
    DB.database withSession {
      query.update(user.id, user.name, user.email, user.role, user.password)
      user
    }
  }

  def deleteUser(id: Int) {
    val query = for (u <- Users if u.id === id) yield(u)
    DB.database withSession {
      query.mutate(_.delete)
    }
  }

}
