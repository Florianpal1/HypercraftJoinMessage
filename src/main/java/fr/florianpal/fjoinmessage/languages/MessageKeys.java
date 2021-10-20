package fr.florianpal.fjoinmessage.languages;


import co.aikar.locales.MessageKey;
import co.aikar.locales.MessageKeyProvider;

public enum MessageKeys implements MessageKeyProvider {

    DATABASEERROR,

    JOINMESSAGE_RELOAD;

    private static final String PREFIX = "hypercraft";

    private final MessageKey key = MessageKey.of(PREFIX + "." + this.name().toLowerCase());

    public MessageKey getMessageKey() {
        return key;
    }
}
