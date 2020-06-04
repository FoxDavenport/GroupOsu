package DatabaseInfo;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class PushScore {
	
	public PushScore(String player, int score, String lvl)
	{
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
	            .withRegion("us-east-1")
	            .build();
	        
	        DynamoDB db = new DynamoDB(client);
	        
	        Table t = db.getTable("RhythmGameScores");
	        
	        try {
	            System.out.println("Adding a new item...");
	            PutItemOutcome outcome = t
		                .putItem(new Item().withPrimaryKey("LevelName", lvl, "Score", score).with("Name", player));

	            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

	        }
	        catch (Exception e) {
	            System.err.println("Unable to add item: ");
	            System.err.println(e.getMessage());
	        }
	}

}
