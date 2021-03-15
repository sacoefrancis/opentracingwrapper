package simpleproducer;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.TracingProducerInterceptor;
import io.opentracing.util.GlobalTracer;

public class CustomProducerInterceptor<K, V> extends TracingProducerInterceptor<K, V> {

    public CustomProducerInterceptor() {
        super();
        System.out.println("***************************************************");
        System.out.println(System.getenv("JAEGER_SERVICE_NAME"));
        Tracer tracer = Configuration.fromEnv().getTracer();
        System.out.println(tracer);
        System.out.println("***************************************************");
        GlobalTracer.registerIfAbsent(tracer);
//        super();
    }
}
