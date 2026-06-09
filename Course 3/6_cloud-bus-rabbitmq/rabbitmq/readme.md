# Build the image
docker build -t my-rabbitmq .

# Run the container
docker run -d --hostname rabbitmq \
--name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
my-rabbitmq