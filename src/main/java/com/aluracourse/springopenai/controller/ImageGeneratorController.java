package com.aluracourse.springopenai.controller;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Para generar imagenes con ayuda de inteligencia artificial.
@RestController
@RequestMapping("/image-generator")
public class ImageGeneratorController {

    private final ImageModel imageModel;

    public ImageGeneratorController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    // Generar imagenes a partir de un prompt.
    @GetMapping("/send")
    public String generateImage(String prompt) {
        // Para configurar las propiedades de la imagen.
        var options = ImageOptionsBuilder
                            .builder()
                            .withHeight(1024)
                            .withWidth(1024)
                            .build();

        // Generar la imagen a partir del prompt.
        var response = imageModel.call(new ImagePrompt(prompt, options));

        // Retornar la URL de la imagen generada.
        return response.getResult().getOutput().getUrl();
    }
}
