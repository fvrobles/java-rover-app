# Java Rover app

## Usage: 
To run the application, you need to have Java installed on your machine (JRE or JDK compatible with Java 21). You can run the application using the following command:
```
java -jar rover.jar input.txt
```

## How to build the application:
1. Clone the repository:
   ```
   git clone https://github.com/fvrobles/java-rover-app.git
   ```
2. Navigate to the project directory:
   ```
   cd ./java-rover-app
   ```
3. Build the project using Maven 3.9.7 and Java 21:
   ```
    mvn clean package
    ```
4. The JAR file will be created in the `target` directory, named `rover.jar`.


## Input file format:
The first line of input is the upper-right coordinates of the plateau, the
lower-left coordinates are assumed to be 0,0.

The rest of the input is information pertaining to the rovers that have
been deployed. Each rover has two lines of input. The first line gives the
rover's position, and the second line is a series of instructions telling
the rover how to explore the plateau.

The position is made up of two integers and a letter separated by spaces,
corresponding to the x and y co-ordinates and the rover's orientation.

Each rover will be finished sequentially, which means that the second rover
won't start to move until the first one has finished moving.

## Example of input.txt file:
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```
