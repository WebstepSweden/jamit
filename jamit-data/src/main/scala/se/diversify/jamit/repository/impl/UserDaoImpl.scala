package se.diversify.jamit.repository.impl

import se.diversify.jamit.db._
import se.diversify.jamit.repository.{DB, UserDao}
import org.scalaquery.ql.{Query, SimpleFunction}

/**Implementation of UserDao */
class UserDaoImpl extends UserDao {

  import org.scalaquery.ql.extended.MySQLDriver.Implicit._
  import org.scalaquery.session.Database.threadLocalSession
  import se.diversify.jamit.domain.User

  override def get(id: Int): User = {
    val query = for (u <- Users if u.id === id) yield (u.id ~ u.name ~ u.email ~ u.phone ~ u.role ~ u.password)
    DB.database withSession {
      val user = query.first
      User(user._1, user._2, user._3, user._4, user._5, user._6)
    }
  }

  override def update(user: User): User = {
    val query = for (u <- Users if u.id === user.id) yield (u.id ~ u.name ~ u.email ~ u.phone ~ u.role ~ u.password)
    DB.database withSession {
      query.update(user.id, user.name, user.email, user.phone, user.role, user.password)
      user
    }
  }

  override def add(user: User): User = {
    DB.database withSession {
      Users.insert(
        (0, user.name, user.email, user.phone, user.role, user.password)
      )
      val scopeIdentity = SimpleFunction.nullary[Int]("LAST_INSERT_ID")
      val newId = Query(scopeIdentity).first
      user.copy(id = newId)
    }
  }

  override def delete(id: Int) {
    val query = for (u <- Users if u.id === id) yield (u)
    DB.database withSession {
      query.mutate(_.delete)
    }
  }

  override def getAll(): List[User] = {
    val query = for (u <- Users) yield (u.id ~ u.name ~ u.email ~ u.phone ~ u.role ~ u.password)
    DB.database withSession {
      query.list.map(user => User(user._1, user._2, user._3, user._4, user._5, user._6))
    }
  }

  override def getByEmail(email: String): User = {
    val query = for (u <- Users if u.email === email) yield (u.id ~ u.name ~ u.email ~ u.phone ~ u.role ~ u.password)
    DB.database withSession {
      val user = query.first
      User(user._1, user._2, user._3, user._4, user._5, user._6)
    }
  }
}
