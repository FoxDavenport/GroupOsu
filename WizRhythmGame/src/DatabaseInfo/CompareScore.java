package DatabaseInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

public class CompareScore {
	
	static AmazonDynamoDB dynamoDB;
	
	static ArrayList<Integer> scores;
	public CompareScore(String mapName, int score, String playerName)
	{
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion("us-east-1")
            .build();
        
        DynamoDB db = new DynamoDB(client);
        
        Table t = db.getTable("RhythmGameScores");
        
        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#lvlname", "LevelName");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":lvl", "Blue");
        
        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#lvlname = :lvl").withNameMap(nameMap).withValueMap(valueMap);

        
        try {
            ItemCollection<QueryOutcome> items = t.query(querySpec);

            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()) {
                Item item = iter.next();
                scores.add(item.getInt("Score"));
            }

        }
        catch (Exception e) {
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }
        
        CheckForHighScore(mapName, score, playerName);
    }
	
	public void CheckForHighScore(String mapName, int score, String playerName)
	{
		PushScore a;
		a  = new PushScore(playerName, score, mapName);
		
	}
	}
