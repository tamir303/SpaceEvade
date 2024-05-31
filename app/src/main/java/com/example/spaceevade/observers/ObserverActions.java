package com.example.spaceevade.observers;

public interface ObserverActions {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
