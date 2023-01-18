package org.kazu.entities

import org.kazu.services.AbstractQuarkusTestService
import software.amazon.awssdk.services.dynamodb.model.AttributeValue


class QuarkusTest (
    val name: String,
    val description: String
) {

    companion object {
        fun from(item: Map<String?, AttributeValue>?): QuarkusTest? {
            if (!item.isNullOrEmpty()) {
                val name = item[AbstractQuarkusTestService.TEST_NAME_COL]?.s() ?: ""
                val description = (item[AbstractQuarkusTestService.TEST_DESCRIPTION_COL]?.s()) ?: ""

                return QuarkusTest(name, description)
            }
            println("no entity found")
            return null
        }
    }
}