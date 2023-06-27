package com.bingo.neo4j;

import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

/**
 * @author bingoabin
 * @date 2023/6/27 16:59
 * @Description:
 */
public class Neo4jTest {
	public static void main(String[] args) {
		getNeo4j();
	}

	public static void getNeo4j() {
		String uri = "neo4j://localhost:7687";
		String username = "neo4j";
		String password = "bingo";

		try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password)); Session session = driver.session()) {
			String query = "MATCH (u:User)-[r:BUY]->(p:Product) RETURN u, r, p";
			Result result = session.run(query);
			while (result.hasNext()) {
				Record record = result.next();
				Node userNode = record.get("u").asNode();
				Relationship relationship = record.get("r").asRelationship();
				Node productNode = record.get("p").asNode();

				// Process and output the retrieved data as needed
				System.out.println("User: " + userNode.get("name") + ", Product: " + productNode.get("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
