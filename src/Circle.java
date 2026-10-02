public class Circle extends Shape {

    private int radius;

    public Circle(String id, Renderer renderer) {
        super(id, renderer);
        this.radius = 2;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return renderer.renderCircle(radius);
    }
}