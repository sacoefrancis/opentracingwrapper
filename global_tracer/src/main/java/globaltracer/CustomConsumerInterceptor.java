package globaltracer;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.TracingConsumerInterceptor;
import io.opentracing.util.GlobalTracer;

public class CustomConsumerInterceptor<K, V> extends TracingConsumerInterceptor<K, V> {

    public CustomConsumerInterceptor() {
        super();
        System.out.println("***************************************************");
        System.out.println(System.getenv("JAEGER_SERVICE_NAME"));
        Tracer tracer = Configuration.fromEnv().getTracer();
        System.out.println(tracer);
        System.out.println("***************************************************");
        GlobalTracer.registerIfAbsent(tracer);
    }
}
