FROM rabbitmq:3.13-management

# Copy custom config
COPY rabbitmq.conf /etc/rabbitmq/rabbitmq.conf

# Copy enabled plugins
COPY enabled_plugins /etc/rabbitmq/enabled_plugins

# Expose AMQP and Management UI ports
EXPOSE 5672 15672
