package com.aluracourse.springopenai.service;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.ModelType;
import org.springframework.stereotype.Service;
import com.knuddels.jtokkit.api.EncodingRegistry;

// Para contar los tokens usados en un prompt.
@Service
public class TokensCounterService {

    public int tokensCounter(String system, String user) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncodingForModel(ModelType.GPT_4O);

        return enc.countTokens(system + user);
    }
}
