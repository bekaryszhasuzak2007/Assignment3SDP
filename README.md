# Assignment 3 — Bridge Pattern

## Student Information

- Name: Bekarys Zhassuzakh
- Group: SE-2527
- Topic: A — Drawing
- GitHub Repository: Assignment3SDP
- Base Commit: `88e37a37543f89a3bc5b1ee6bf56e1f046a04506`

## Bridge Pattern Structure

This project demonstrates the Bridge Pattern for drawing shapes with different renderers.

The pattern separates two independent dimensions:

1. Shapes — `Circle` and `Square`
2. Renderers — `VectorRenderer`, `RasterRenderer`, and `AsciiRenderer`

### Abstraction Hierarchy

- `Shape`
- `Circle`
- `Square`

### Implementor Hierarchy

- `Renderer`
- `VectorRenderer`
- `RasterRenderer`
- `AsciiRenderer`

## Role Map

| Role | Class | Source |
|---|---|---|
| Abstraction | `Shape` | `src/Shape.java` |
| A1 | `Circle` | `src/Circle.java` |
| A2 | `Square` | `src/Square.java` |
| Implementor | `Renderer` | `src/Renderer.java` |
| I1 | `VectorRenderer` | `src/VectorRenderer.java` |
| I2 | `RasterRenderer` | `src/RasterRenderer.java` |
| I3 | `AsciiRenderer` | `src/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

## Bridge

The `Shape` class stores an interface-typed reference to the `Renderer`.

The bridge field is:

```java
protected Renderer renderer;
```

The renderer is supplied through the `Shape` constructor.

This connects the two class hierarchies using composition.

The abstraction classes do not create concrete renderers, cast renderers, or check their concrete types.

## Main Methods

### execute()

The `Shape` class declares:

```java
public abstract String execute();
```

`Circle` and `Square` implement this method and delegate the rendering operation to the stored `Renderer`.

For example, `Circle` uses:

```java
return renderer.renderCircle(radius);
```

### setImplementation()

The renderer can be changed at runtime:

```java
public void setImplementation(Renderer renderer) {
    this.renderer = renderer;
}
```

The parameter type is the `Renderer` interface.

This allows the same shape object to work with different renderer implementations.

## Runtime Switching — T5

T5 uses the same `Circle` object.

First, the circle uses `VectorRenderer`. Then its renderer is replaced with `RasterRenderer`.

The test checks:

- the same object using `==`
- the same ID
- the same domain data
- the actual result before switching
- the actual result after switching

The actual results are:

```text
before=VECTOR circle radius=2
after=RASTER circle radius=2
```

The object itself is not replaced. Only its renderer reference is changed.

The circle ID and radius remain unchanged.

## Extension — I3

The base version was completed first with:

- `Circle`
- `Square`
- `Renderer`
- `VectorRenderer`
- `RasterRenderer`

The base version was committed before adding the third implementation.

The extension adds `AsciiRenderer` without changing:

- `Shape`
- `Circle`
- `Square`
- `Renderer`
- `VectorRenderer`
- `RasterRenderer`

Only the new `AsciiRenderer` class and the demonstration code in `Main` were added or changed in the Java source files.

### Extension Commit

```text
7d6a5ec2a5f7b325e5388841538f6fd0f7e1326f
```

The extension adds `AsciiRenderer` and tests T6 and T7.

## Commits

### Base Commit

```text
88e37a37543f89a3bc5b1ee6bf56e1f046a04506
```

The base commit contains the working 2x2 combination of two shapes and two renderers, including runtime switching in T5.

### Extension Commit

```text
7d6a5ec2a5f7b325e5388841538f6fd0f7e1326f
```

The extension commit adds `AsciiRenderer` and the T6/T7 demonstration checks.

## Expected Results

- T1: `Circle + VectorRenderer` → `VECTOR circle radius=2`
- T2: `Circle + RasterRenderer` → `RASTER circle radius=2`
- T3: `Square + VectorRenderer` → `VECTOR square side=3`
- T4: `Square + RasterRenderer` → `RASTER square side=3`
- T5: same `Circle` object; before switching → `VECTOR circle radius=2`; after switching → `RASTER circle radius=2`
- T6: `Circle + AsciiRenderer` → `ASCII circle radius=2`
- T7: `Square + AsciiRenderer` → `ASCII square side=3`

## Compilation

Use Java JDK 17:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

## Run Demo

```bash
java -cp out Main --demo
```

## Test Results

The submitted program produces the following output:

```text
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3
T5 PASS | sameObject=true | stateUnchanged=true | before=VECTOR circle radius=2 | after=RASTER circle radius=2
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
SUMMARY: 7/7 PASS
```

## Project Files

- `src/` — all Java source files
- `sources.txt` — list of Java source files
- `README.md` — project documentation
- `demo-output.txt` — captured console output from all seven checks
- `extension.diff` — source diff between the base commit and the extension commit

## Bridge Pattern Summary

The Bridge Pattern separates an abstraction from its implementation.

In this project:

- `Shape` is the abstraction.
- `Circle` and `Square` are refined abstractions.
- `Renderer` is the implementor interface.
- `VectorRenderer`, `RasterRenderer`, and `AsciiRenderer` are concrete implementors.
- `Main` is the client.

The same shape can work with different renderers without creating a separate subclass for every shape-renderer combination.
```