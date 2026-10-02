public class Square extends Shape {

    private int side;

    public Square(String id, Renderer renderer) {
        super(id, renderer);
        this.side = 3;
    }

    public int getSide() {
        return side;
    }

    @Override
    public String execute() {
        return renderer.renderSquare(side);
    }
}