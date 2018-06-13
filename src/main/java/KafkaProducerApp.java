import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "BROKER-1:9092, BROKER-2:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //KafkaProducer myProducer = new KafkaProducer(props);

        KafkaProducer<String,  String> myProducer = new KafkaProducer<String, String>(props);
        //2nd arg is based on value.serializer
        //ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value)
        //ProducerRecord myRecord = new ProducerRecord("my_topic", "Course-001", "My Message 1");

        //myProducer.send(myRecord); //best practice: try..catch

        try {
            for(int i = 0; i < 150; i++) {
                myProducer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), "MyMessage: " + Integer.toString(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //avoid memory leaks
            myProducer.close();
        }

    }

}
