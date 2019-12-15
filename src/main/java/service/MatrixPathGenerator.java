package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Matrix;
import model.MatrixPath;
import model.MatrixPoint;

public class MatrixPathGenerator {

    private final Random random = new Random();

    public MatrixPath generateNewPath(Matrix matrix) {
        List<MatrixPoint> points = new ArrayList<>(matrix.getDimension());

        points.add(new MatrixPoint(0, 0));
        for (int i = 0; i < 2 * matrix.getDimension() - 2; ++i) {
            MatrixPoint currentPoint = points.get(points.size() - 1);

            if (bothPointsUnavailable(matrix, currentPoint)) {
                throw new IllegalArgumentException("Can't go to the new matrix point");
            }
            MatrixPoint matrixPoint = chooseRandomNewNextPoint(matrix, currentPoint);
            points.add(matrixPoint);

            List<MatrixPoint> nextPossiblePoints = generatePossiblePointsForNextMove(matrix, matrixPoint);
            MatrixPoint randomElement = getRandomElement(nextPossiblePoints);
            addAllPoints(points, randomElement);
        }

        return new MatrixPath(points);
    }

    private void addAllPoints(List<MatrixPoint> points, MatrixPoint matrixPoint) {
        MatrixPoint last = points.get(points.size() - 1);
        if (last == matrixPoint) {
            return;
        }
        if (onTheDiagonal(matrixPoint, last)) {
            int incX = sign(matrixPoint.getX() - last.getX());
            int incY = sign(matrixPoint.getY() - last.getY());

            for (int i = 1; i <= Math.abs(last.getX() - matrixPoint.getX()); ++i) {
                points.add(new MatrixPoint(last.getX() + incX * i, last.getY() + incY * i));
            }
        } else {
            throw new IllegalStateException("unreachable statement");
        }
    }

    private boolean onTheDiagonal(MatrixPoint matrixPoint, MatrixPoint last) {
        return Math.abs(last.getX() - matrixPoint.getX()) == Math.abs(last.getY() - matrixPoint.getY());
    }

    private int sign (int x) {
        return Integer.compare(x, 0);
    }

    private MatrixPoint getRandomElement(List<MatrixPoint> nextPossiblePoints) {
        return nextPossiblePoints.get(random.nextInt(nextPossiblePoints.size()));
    }

    private List<MatrixPoint> generatePossiblePointsForNextMove(Matrix matrix, MatrixPoint matrixPoint) {
        List<MatrixPoint> res = new ArrayList<>();
        res.add(matrixPoint);

        int x = matrixPoint.getX() - 1, y = matrixPoint.getY() + 1;
        while (x >= 0 && y < matrix.getDimension() && matrix.get(x, y) == 1) {
            res.add(new MatrixPoint(x, y));
            x -= 1;
            y += 1;
        }

        x = matrixPoint.getX() + 1;
        y = matrixPoint.getY() - 1;
        while (x < matrix.getDimension() && y >= 0 && matrix.get(x, y) == 1) {
            res.add(new MatrixPoint(x, y));
            x += 1;
            y -= 1;
        }

        return res;
    }

    private MatrixPoint chooseRandomNewNextPoint(Matrix matrix, MatrixPoint currentPoint) {
        List<MatrixPoint> availablePoints = new ArrayList<>();
        if (currentPoint.getX() + 1 < matrix.getDimension() &&
                matrix.get(currentPoint.getX() + 1, currentPoint.getY()) == 1) {
            availablePoints.add(new MatrixPoint(currentPoint.getX() + 1, currentPoint.getY()));
        }
        if (currentPoint.getY() + 1 < matrix.getDimension() &&
                matrix.get(currentPoint.getX(), currentPoint.getY() + 1) == 1) {
            availablePoints.add(new MatrixPoint(currentPoint.getX(), currentPoint.getY() + 1));
        }
        return getRandomElement(availablePoints);
    }

    private boolean bothPointsUnavailable(Matrix matrix, MatrixPoint currentPoint) {
        return (currentPoint.getX() + 1 < matrix.getDimension()
                && matrix.get(currentPoint.getX() + 1, currentPoint.getY()) == 0)
                && (currentPoint.getY() + 1 < matrix.getDimension()
                && matrix.get(currentPoint.getX(), currentPoint.getY() + 1) == 0);
    }
}
