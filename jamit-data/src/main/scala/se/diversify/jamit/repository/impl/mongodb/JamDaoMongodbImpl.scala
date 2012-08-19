package se.diversify.jamit.repository.impl.mongodb

import se.diversify.jamit.repository.JamDao
import se.diversify.jamit.domain.Jam

class JamDaoMongodbImpl extends JamDao with BaseDaoMongodbImpl[Jam] {

  override val modelCompanion = Jam
}