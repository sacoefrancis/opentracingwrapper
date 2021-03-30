# opentracingwrapper


curl -X POST http://localhost:8083/connectors -H "Content-Type: application/json" --data '{
  "name":"file-sink-connector-002",
  "config": {
    "tasks.max":"1",
    "batch.size":"1000",
    "batch.max.rows":"1000",
    "poll.interval.ms":"500",
    "connector.class":"org.apache.kafka.connect.file.FileStreamSinkConnector",
    "file":"/var/lib/kafka/data/names.txt",
    "topics":"number-topic-7",
    "key.converter": "org.apache.kafka.connect.storage.StringConverter",
    "key.converter.schemas.enable":"false",
    "value.converter": "org.apache.kafka.connect.storage.StringConverter",
    "key.converter.schemas.enable":"false"
  }
}'
