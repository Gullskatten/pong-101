package no.ntnu.espegu.observer;

import java.util.List;

public interface ActionObserver {
    void update(String message);
    List<String> notificationTypes();
}
