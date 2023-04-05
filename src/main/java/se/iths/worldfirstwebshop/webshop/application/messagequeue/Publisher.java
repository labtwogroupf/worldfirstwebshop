package se.iths.worldfirstwebshop.webshop.application.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import se.iths.worldfirstwebshop.webshop.application.dto.InventoryDto;
import se.iths.worldfirstwebshop.webshop.application.dto.ProductDto;
import se.iths.worldfirstwebshop.webshop.application.service.ShopService;

@Service
public class Publisher {

    RabbitTemplate template;
    String ORDER_QUEUE = "orderQueue";
    ObjectMapper objectmapper;
    ShopService shopService;

    public Publisher(RabbitTemplate template, ObjectMapper objectmapper, ShopService shopService) {
        this.template = template;
        this.objectmapper = objectmapper;
        this.shopService = shopService;
    }

    public void addToQueue(ProductDto product, int amount) {
        var inventory = new InventoryDto(amount, product);
        try {
            Message message = getMessageAsJson(objectmapper.writeValueAsString(inventory));
            template.convertAndSend(ORDER_QUEUE, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(ORDER_QUEUE);
        container.setMessageListener(getMessageAndAddToCart());
        return container;

    }

    @NotNull
    private MessageListener getMessageAndAddToCart() {
        return message -> {
            var messageFromQueue = new String(message.getBody());
            try {
                var inventory = objectmapper.readValue(messageFromQueue, InventoryDto.class);
                shopService.addToCart(inventory.getProduct(), inventory.getAmount());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static Message getMessageAsJson(String json) {
        return MessageBuilder
                .withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
    }

    @Bean
    public Queue queue() {
        return new Queue(ORDER_QUEUE);
    }
}
