# **TP_Trains**
A simulation of trains running on a railway track consisting of different sections and stations. 

## **Class Diagram**:
![Diagramme.png](Diagramme.png)

### **Overview of Each Component**:

1. **Train (Train.java)**  
   The `Train` class represents a train with a name and a position on the railway track. A train can move to the next element of the track. The simulation is managed in a thread using the `Runnable` interface.

2. **Element (Element.java)**  
   The `Element` class is an abstract class that represents different types of elements on the railway track, such as stations and sections. It handles the logic for moving trains between elements.

3. **Section (Section.java)**  
   The `Section` class, a concrete subclass of `Element`, represents a section of the railway track. It keeps track of whether the section is occupied by a train and how many trains are present.

4. **Station (Station.java)**  
   The `Station` class, a concrete subclass of `Element`, represents a station on the railway track. It has a maximum capacity for the number of trains it can accommodate and manages the entry and exit of trains.

5. **Position (Position.java)**  
   The `Position` class represents the position of a train on the railway track, characterized by an element and a direction (left to right or right to left). It allows the train to move to the next element.

6. **Direction (Direction.java)**  
   The `Direction` enumeration represents the direction of a train, either left to right (`LR`) or right to left (`RL`).

7. **Railway (Railway.java)**  
   The `Railway` class represents the complete railway circuit, consisting of elements like stations and sections. It also tracks the number of trains in each direction.

8. **BadPositionForTrainException (BadPositionForTrainException.java)**  
   This class is a custom exception that is thrown when a train is improperly positioned on the railway track.

---

## **Usage**:

To run the simulation, you can either:

- Run the `Main` class and observe the movement of the trains on the console.
- Run the `TrainSimulationApp` class to see the movement through a graphical user interface.

You can manipulate the elements of the track, such as the number of trains, stations, or sections, by modifying the `Main` or `TrainSimulationApp` classes to test the behavior of trains in various scenarios.

---

### **Requirements**:
- Java Development Kit (JDK) 8 or later.
- A development environment such as Eclipse, IntelliJ IDEA, or any IDE that supports Java.
