package com.dev.taskservice.entity.generator

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serializable
import java.util.UUID

class IdGenerator : IdentifierGenerator {
    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): Serializable {
        return UUID.randomUUID().toString().replace("-", "")
    }

    companion object {
        fun generate(): String {
            return UUID.randomUUID().toString().replace("-", "")
        }
    }
}
