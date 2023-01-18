package org.kazu.services

import org.kazu.entities.QuarkusTest
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import kotlin.streams.toList

@ApplicationScoped
class QuarkusTestService: AbstractQuarkusTestService() {

    @Inject
    private lateinit var dynamoDbClient: DynamoDbClient

    fun findAll(): List<QuarkusTest?> {
        return dynamoDbClient.scanPaginator(scanRequest()).items().stream().map(QuarkusTest::from).toList()
    }

    fun findByName(name: String): QuarkusTest? {
        return QuarkusTest.from(dynamoDbClient.getItem(getItemRequest(name)).item())
    }

    fun add(entity: QuarkusTest): QuarkusTest? {
        dynamoDbClient.putItem(putItemRequest(entity))
        return findByName(entity.name)
    }
}