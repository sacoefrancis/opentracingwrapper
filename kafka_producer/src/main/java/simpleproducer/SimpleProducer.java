package simpleproducer;

//import java.lang.module.Configuration;
import java.util.*;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.TracingProducerInterceptor;
import io.opentracing.util.GlobalTracer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


public class SimpleProducer {

    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static JaegerTracer initTracer(String service) {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        Configuration config = new Configuration(service).withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }

    public static void main(String[] args) {
//        SetTracer setNewTracer = new SetTracer();
//        Tracer tracer = initTracer("hello-world");
        System.out.println(System.getenv("JAEGER_SERVICE_NAME"));
//        System.out.println(System.getenv("sample_key"));
//        System.out.println(args[0]);
//        Tracer tracer = TracerResolver.resolveTracer();
//        Tracer tracer = Configuration.fromEnv().getTracer();
//        GlobalTracer.registerIfAbsent(tracer);
        Scanner scan = new Scanner(System.in);
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

//        Producer<String, String> producer = new KafkaProducer<>(props);
//        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
//                TracingProducerInterceptor.class.getName());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, CustomProducerInterceptor.class.getName());

        System.out.println(props.toString());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
//        System.out.println(producer.toString());
        while(true)  {
            String name = scan.nextLine();
            String[] status = {"Delivery", "Bounce", "Complaint"};
            String textToSend = "{subject: "+name+", status:"+getRandom(status)+"}";
//            TracingKafkaProducer<String, String> tracingProducer = new TracingKafkaProducer<>(producer, tracer);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("sample-topic-4", "Name", textToSend);
//            producerRecord.headers().add("traceId", String.valueOf(traceId).getBytes()); #add custom header to record
//            producer.send(producerRecord); # basic producer with out opentracing
//            tracingProducer.send(producerRecord);
            producer.send(producerRecord);
            if(name.equals("exit"))
                break;
        }
        producer.close();
    }
}
