package org.cs;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


import java.io.IOException;

/**
 * Created by Gibson on 1/28/20.
 */
public class ImportIntoES implements Runnable {

    // Logger logger = LoggerFactory.getLogger(ImportIntoES.class);

    private String SERVER_HOSTNAME = "172.27.98.129";
    private int SERVER_PORT = 9200;
    private String SERVER_PROTOCOL = "http";
    private String INDEX_NAME = "am";
    private String RECORD_NAME = "txn";
    private Record record;
    private RestHighLevelClient client;


    public ImportIntoES(Record record) throws IOException {
        this.client = createClient();
        this.record = record;
    }

    @Override
    public void run() {

        try {

            // Convert Object to JSON for Storage in ES
            String jsonRecord = new Gson().toJson(record);

            IndexRequest indexRequest = new IndexRequest(INDEX_NAME, RECORD_NAME, record.getTransactionNumber()).
                    source(jsonRecord, XContentType.JSON);

            IndexResponse indexResponse = this.client.index(indexRequest, RequestOptions.DEFAULT);

            String id = indexResponse.getId();

            System.out.println("Logger ID | " + id + " | " + indexResponse.status().getStatus());

            this.client.close();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public RestHighLevelClient createClient() throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(SERVER_HOSTNAME, SERVER_PORT, SERVER_PROTOCOL)));
        return client;
    }
}
