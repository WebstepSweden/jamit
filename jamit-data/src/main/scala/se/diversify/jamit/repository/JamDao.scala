package se.diversify.jamit.repository

import impl.mongodb.JamDaoMongodbImpl
import se.diversify.jamit.domain.Jam


abstract class JamDao extends BaseDao[Jam]

object JamDao {
  val defaultDao = new JamDaoMongodbImpl
}

