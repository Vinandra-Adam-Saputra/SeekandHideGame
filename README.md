# Hide and Seek Game

Hide and Seek Game is a Java-based game that features a battle between red droids (hunters) and a green droid (hider) in a randomly generated maze.

## Features

1. Randomly Generated Maze: Each game has a unique maze generated using a recursive backtracking algorithm.
2. Red and Green Droids: Red droids act as hunters, while the green droid tries to hide.
3. Simple AI: Red droids use a BFS (Breadth-First Search) algorithm to find the shortest path to the green droid.
4. Game Controls: Buttons to start, stop, randomize the map, and randomize droid positions.
5. Droid Count Management: Ability to add or remove red droids.
6. Adjustable Vision Range: Slider to set the green droid's vision range.
7. View Modes: Buttons to toggle between red and green droid views.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Swing Library

## How to Run

1. Compile the Java file:
2. Run the program:

## How to Play

1. Click the "Mulai" (Start) button to begin the game.
2. Use the "Acak Map" (Randomize Map) button to generate a new maze.
3. Use "Acak Droid Merah" (Randomize Red Droid) and "Acak Droid Hijau" (Randomize Green Droid) buttons to change droid positions.
4. Adjust the number of red droids with "Tambah Droid Merah" (Add Red Droid) and "Kurangi Droid Merah" (Remove Red Droid) buttons.
5. Use the slider to adjust the green droid's vision range.
6. Use "Pandangan Droid Merah" (Red Droid View) and "Pandangan Droid Hijau" (Green Droid View) buttons to switch between view modes.
7. The game ends when a red droid captures the green droid.

## Game Components

- `HideAndSeek`: Main class that sets up the game board and controls.
- `Droid`: Class representing the droids with movement logic.
- `Node`: Helper class for pathfinding algorithm.

## Key Methods

- `generateMaze()`: Creates a random maze using recursive backtracking.
- `moveDroids()`: Handles the movement of all droids.
- `bfs()`: Implements Breadth-First Search for red droid pathfinding.
- `paintComponent()`: Renders the game board and droids.

## Contributing

Contributions are always welcome. Please fork this repository, make your changes, and submit a pull request for any improvements or additional features.

## License

[Add license information here]

## Contact

For questions or suggestions, please contact:

- Email: adamvinandra767@gmail.com
- Instagram: @vinandradam
