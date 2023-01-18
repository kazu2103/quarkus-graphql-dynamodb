package org.kazu.services

import org.kazu.entities.QuarkusTest
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import software.amazon.awssdk.services.dynamodb.model.ScanRequest

abstract class AbstractQuarkusTestService {

    companion object {
        const val TEST_NAME_COL = "name"
        const val TEST_DESCRIPTION_COL = "description"
    }

    fun showTableName(): String {
        return "QuarkusTest"
    }

    protected fun scanRequest(): ScanRequest {
        return ScanRequest.builder().tableName(showTableName()).attributesToGet(Companion.TEST_NAME_COL, TEST_DESCRIPTION_COL).build()
    }

    fun putItemRequest(entity: QuarkusTest): PutItemRequest {
        val item = mutableMapOf<String, AttributeValue>()
        item[Companion.TEST_NAME_COL] = AttributeValue.builder().s(entity.name).build()
        item[TEST_DESCRIPTION_COL] = AttributeValue.builder().s(entity.description).build()

        return PutItemRequest.builder()
            .tableName(showTableName())
            .item(item)
            .build()
    }

    fun getItemRequest(name: String): GetItemRequest {
        val item = mutableMapOf<String, AttributeValue>()
        item[Companion.TEST_NAME_COL] = AttributeValue.builder().s(name).build()

        return GetItemRequest.builder()
            .tableName(showTableName())
            .attributesToGet(Companion.TEST_NAME_COL, TEST_DESCRIPTION_COL)
            .build()

    }


}