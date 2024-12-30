package com.aluracourse.springopenai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    // Constructor.
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o-mini") // Configuracion del modelo a nivel global en esta clase.
                        .build())
                .build();
    }

    /*
     * Ejemplo de como hacer una consulta a la API de Open AI utilizando uno
     * de sus modelos de IA Generativa.
    */
    @GetMapping("/send")
    public String generation(String prompt) {
        String systemRole = """
                            Eres un ingeniero de software, las preguntas que te realicen,
                            las responderas primero con una definicion tecnica,
                            luego utilizaras una analogia para que puedan entenderte mejor.
                            """;

        return this.chatClient.prompt()
                .system(systemRole) // Para personalizar las respuestas.
                .user(prompt) // El prompt del usuario.

                // Para personalizar los parametros de comportamiento.
                .options(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-3.5-turbo") // Configuracion del modelo a nivel local en este metodo.
                        .withTemperature(0.80) // Temperatura.
                        .withMaxTokens(4096) // Maximo de tokens.
                        .build()
                )
                .advisors(new SimpleLoggerAdvisor()) // Para habilitar logs.
                .call()
                .content();
    }
}
