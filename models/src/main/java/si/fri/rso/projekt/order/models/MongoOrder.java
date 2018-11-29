package si.fri.rso.projekt.order.models;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoOrder {


    private final String DBUser       = "root";
    private final String DBPassword   = "13tpxnxJTwUScc3V";
    private final String DBName       = "db-orders";
    private final String DBCollection = "orders";

    private MongoClient connectDB() {
        MongoClientURI uri = new MongoClientURI("mongodb://"+ DBUser +":"+ DBPassword +"@gsascluster-shard-00-00-ocnkx.azure.mongodb.net:27017," +
                "gsascluster-shard-00-01-ocnkx.azure.mongodb.net:27017,gsascluster-shard-00-02-ocnkx.azure.mongodb.net:27017/test?" +
                "ssl=true&replicaSet=gsasCluster-shard-0&authSource=admin&retryWrites=true");

        return new MongoClient(uri);
    }

    public List<Order> getAllOrders() {
        MongoClient client = connectDB();

        MongoDatabase db = client.getDatabase(DBName);

        MongoCollection<Document> orderCollection = db.getCollection(DBCollection);

        List<Order> results = new ArrayList<>();

        for(Document curr : orderCollection.find()) {

            Order order = new Order(curr.getInteger("orderID"),
                                    curr.getInteger("restaurantID"),
                                    curr.getInteger("billID"),
                                    curr.getInteger("buyerID"));

            results.add(order);
        }

        return results;
    }

    public Order getOrder(Integer orderID) {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> bc = db.getCollection(DBCollection);

        Bson filter = Filters.eq("orderID", orderID);

        Document result = bc.find(filter).first();

        if(result == null) {
            return null;
        }


        return new Order(result.getInteger("orderID"),
                result.getInteger("restaurantID"),
                result.getInteger("billID"),
                result.getInteger("buyerID"));
    }
}
