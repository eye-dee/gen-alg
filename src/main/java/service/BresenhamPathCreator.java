package service;

import java.util.ArrayList;
import java.util.List;
import model.MatrixPath;
import model.MatrixPoint;

public class BresenhamPathCreator {

    public MatrixPath createPath(MatrixPoint start, MatrixPoint end) {
        return new MatrixPath(drawBresenhamLine(start.getX(), start.getY(), end.getX(), end.getY()));
    }

    private List<MatrixPoint> drawBresenhamLine(int xStart, int yStart, int xEnd, int yEnd) {
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
        List<MatrixPoint> res = new ArrayList<>();

        dx = xEnd - xStart;
        dy = yEnd - yStart;

        incx = sign(dx);
        incy = sign(dy);

        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;

        if (dx > dy) {
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;
        }

        x = xStart;
        y = yStart;
        err = el / 2;
        res.add(new MatrixPoint(x, y));

        for (int t = 0; t < el; t++) {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;
                y += incy;
            } else {
                x += pdx;
                y += pdy;
            }

            res.add(new MatrixPoint(x, y));
        }
        return res;
    }

    private int sign(int x) {
        return Integer.compare(x, 0);
    }
}
