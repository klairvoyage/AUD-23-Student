### Übungsblatt 1

# Algorithmen und Datenstrukturen

In diesem Bereich finden Sie die erste theoretische Hausübung. Bitte beachten Sie die allgemeinen Hinweise zu den Hausübungen und deren Abgabe im [Moodle-Kurs](https://moodle.informatik.tu-darmstadt.de/course/view.php?id=1399).
Bitte reichen Sie Ihre Abgabe bis spätestens _Freitag, 28.04.2023, 14:00 Uhr MESZ_ ein. Verspätete Abgaben können _nicht_ berücksichtigt werden.

## H1: Türme von Hanoi (6+4+4+3+1 Punkte)

Die _Türme von Hanoi_ sind ein bekanntes Geduldspiel, dessen Regeln hier nochmal kurz erklärt werden: Das Spiel besteht
aus drei Stäben, die wir mit 0, 1 und 2 nummerieren, sowie einer festen Anzahl n∈N<sub>0</sub> gelochter Scheiben mit paarweise
unterschiedlicher Größe. Zu Beginn liegen alle Scheiben auf einem Stab, der Größe nach geordnet, mit der größten Scheibe
ganz unten. Ziel des Spiels ist es, alle Scheiben vom Ausgangsstab auf einen anderen Stab zu versetzen. Dabei müssen folgende Regeln befolgt werden:

- In jedem Spielzug darf immer nur eine Scheibe bewegt und auf einen anderen Stab gelegt werden;
- Während des gesamten Spieles darf niemals eine größere Scheibe über einer kleineren Scheibe liegen.

Als Beispiel finden Sie in der Abbildung unten zwei erlaubte (links) und zwei regelwidrige (rechts) Zustände, bei n = 4 Scheiben:

![zwei erlaubte (links) und zwei regelwidrige (rechts) Zustände bei n = 4 Scheiben](src/main/resources/image1.png)

(a) Geben Sie Pseudocode für einen rekursiven Algorithmus Hanoi(n,i,j) an. Der Algorithmus soll das Spiel mit Ausgangsstab i und Zielstab j lösen,
indem er ein Array mit den ausgeführten Spielzügen ausgibt. Sie können hierbei einen Spielzug vom Stab k auf Stabldurch das Tupel(k,l) angeben und
das Aneinanderhängen von zwei Arrays a und b als a||b notieren. Achten Sie darauf, keine unnötigen Spielzüge auszuführen (siehe auch Teilaufgabe d).

> **Hinweis:** Teilaufgabe d) _Zeigen Sie, dass Ihr Algorithmus „optimal“ ist: Es gibt keinen korrekten Algorithmus, der es ermöglicht, das Spiel in weniger Schritten zu lösen. Falls dies nicht der Fall sein sollte, revidieren Sie Ihren Algorithmus aus a._

## Functionality

The program provides the `hanoi` function which calculates the minimal number of steps and displays the sequence of moves required to solve the Tower of Hanoi game. The function takes three parameters:

```java
public static ArrayList<int[]> hanoi(int n, int i, int j)
```

- `n`: The number of disks in the game.
- `i`: The index of the source rod.
- `j`: The index of the destination rod.

The function returns an `ArrayList` of integer arrays, where each array represents a move from one rod to another. The first element of the array represents the source rod, and the second element represents the destination rod.

The `main` function in the program demonstrates the usage of the `hanoi` function by calling it with the values `(0, 0, 2)`. It prints the minimal number of steps required to solve the game and displays the sequence of moves.

## Example Output

```lua
minimale Anzahl an Schritten: 1
[[0, 2]]
```

In the above example, the minimal number of steps required to solve the game with 0 disks, starting from rod 0 and moving to rod 2, is 1. The move sequence is represented by the array `[[0, 2]]`, which indicates that a disk should be moved from rod 0 to rod 2.

**Note:** The output may vary depending on the input values provided to the `hanoi` function.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the code for personal and commercial purposes.

---
> This [README](README.md) was mainly written by ChatGPT! :)
