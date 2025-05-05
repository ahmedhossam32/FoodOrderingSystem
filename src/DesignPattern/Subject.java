package DesignPattern;


public interface Subject {
   
    void addObserver(ClientObserver observer);

    void addObserver(DeliveryPersonObserver observer);

  
    void removeObserver(ClientObserver observer);

    void removeObserver(DeliveryPersonObserver observer);

  
    void notifyObservers(String type);
}
