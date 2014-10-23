package com.jcwx.game.common.connection.message;

import java.util.Map;

public interface Message {
    Map<String, Object> getContent();

    String getHandlerName();

    void put(String key, Object value);

    void setHandlerName(String handlerName);
}
