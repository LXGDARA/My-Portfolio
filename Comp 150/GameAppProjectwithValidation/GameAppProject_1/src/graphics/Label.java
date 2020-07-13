package graphics;

public class Label extends Shape{

    private Point2D centre;
    private int radius;
    private int dx, dy;

    public Label(int x, int y, int radius, int pixelsIndex, int colorsIndex) {
        // If we do call it, we must call super first.
        super(pixelsIndex, colorsIndex);
        centre = new Point2D(x, y);
        this.radius = radius/2;

    }

    @Override
    public void draw() {
        int x = 0;
        int y = radius;
        int d = 3 - 2*radius;
        drawSymmetricPositions(x, y);
        while (y >= x) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4*(x - y) + 10;
            } else {
                d = d + 4*x + 6;
            }
            drawSymmetricPositions(x, y);
        }
    }
    private void drawSymmetricPositions(int x, int y) {
        // the eight symmetric points on this circle of the point (x, y)
        parentPanel.set(centre.x + x, centre.y + y, pixelsIndex, colorsIndex);

    }
}
