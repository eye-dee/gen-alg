package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

public class MatrixPath {

    @Getter
    private final List<MatrixPoint> points;

    private final Set<MatrixPoint> pointSet;

    public MatrixPath(List<MatrixPoint> points) {
        this.points = points;
        pointSet = new HashSet<>(points);
    }

    public void showPath() {
        String stringPath = points.stream()
                .map((p) -> String.format("(%d, %d)", p.getX(), p.getY()))
                .collect(Collectors.joining(","));
        System.out.println(stringPath);
    }

    public boolean contains(MatrixPoint matrixPoint) {
        return pointSet.contains(matrixPoint);
    }
}
