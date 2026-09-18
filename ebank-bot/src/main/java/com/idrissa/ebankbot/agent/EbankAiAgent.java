package com.idrissa.ebankbot.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class EbankAiAgent {

    ChatClient chatClient;
    String conversationId;
    ToolCallbackProvider toolCallbackProvider;

    //chat client pour le chat avec le llm et chatmemory pour regler le probleme de memoire
    public EbankAiAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous êtes un assistant qui se charge de répondre aux questions de l'utilisateur à propos des clients en
                        fonction du contexte fournis à propos des clients et des comptes bancaires. Si aucun contexte n'est fourni, repond je ne sais pas.
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        this.toolCallbackProvider = toolCallbackProvider;
        this.conversationId= UUID.randomUUID().toString(); //illegalargument exception ocnverstion id cannot be null
    }


    public String chat(String query) {
        return chatClient.prompt(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .tools(toolCallbackProvider)
                .call()
                .content();
    }
}
