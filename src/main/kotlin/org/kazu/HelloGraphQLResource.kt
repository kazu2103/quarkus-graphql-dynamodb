package org.kazu

import org.eclipse.microprofile.graphql.DefaultValue
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Mutation
import org.eclipse.microprofile.graphql.Query
import org.kazu.entities.QuarkusTest
import org.kazu.services.QuarkusTestService
import javax.inject.Inject

@GraphQLApi
class HelloGraphQLResource {

    @Inject
    private lateinit var quarkusTestService: QuarkusTestService

    @Query
    @Description("Say hello")
    fun sayHello(@DefaultValue("World") name: String): String = "Hello $name"

    @Query
    fun findAll(): List<QuarkusTest> {
        return quarkusTestService.findAll().filterNotNull()
    }

    @Query
    fun findByName(name: String): QuarkusTest? {
        return quarkusTestService.findByName(name)
    }

    @Mutation
    fun add(entity: QuarkusTest): QuarkusTest? {
        return quarkusTestService.add(entity)
    }

}