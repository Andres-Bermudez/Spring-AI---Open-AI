package com.aluracourse.springopenai.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;

/* Con esta estructura, centralizamos la configuración de los ChatClients,
 * haciendo que el código sea más limpio y fácil de mantener. Ahora, basta
 * con utilizar la anotación @Qualifier para inyectar el ChatClient deseado
 * en cada clase en la que necesitemos un modelo especifico.
*/
@Configuration
public class OpenAIModels {

    @Bean
    @Qualifier("gpt-4o")
    public ChatClient gpt4oChatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o")
                        .build())
                .build();
    }

    @Bean
    @Qualifier("gpt-4o-mini")
    public ChatClient gpt4oMiniChatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o-mini")
                        .build())
                .build();
    }

    @Bean
    @Qualifier("gpt-3.5-turbo")
    public ChatClient gpt35turboChatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o-mini")
                        .build())
                .build();
    }
}

/*
 * Ejemplo de como se pueden inyectar estos modelos en otra clase:
 *
 * @RestController
 * @RequestMapping("/endpoint")
 * public class MyController {
 *      private final ChatClient chatClient;
 *
 *      public MyController1(@Qualifier("gpt-4o") ChatClient chatClient) {
 *          this.chatClient = chatClient;
 *      }
 *
 *      // Otros metodos del controlador.
 * }
*/
