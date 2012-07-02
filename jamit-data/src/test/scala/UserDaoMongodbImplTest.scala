import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import se.diversify.jamit.domain.{Role, User}
import se.diversify.jamit.repository.impl.mongodb.UserDaoMongodbImpl

@RunWith(classOf[JUnitRunner])
class UserDaoMongodbImplTest extends Specification {

  "UserDaoMongodbImpl" should {
    val dao = new UserDaoMongodbImpl

    "allow inserting a user" in {
      val u = User.create("Kalle Anka", "kalle@anka.se", "0709-123456", Role.Fan, "abc123")
      val newU = dao.add(u)
      newU mustEqual u
    }
  }

}

