package com.company;

import java.util.Collection;
import java.util.concurrent.Callable;
class ParallelLineComputer implements Callable<Collection<Line>> {
    Collection<Point> pointsToComputeIntoLines;
    int pointToUse = 0;

    public ParallelLineComputer(int pointToUse, Collection<Point> pointsToComputeIntoLines) {
        this.pointToUse = pointToUse;
        this.pointsToComputeIntoLines = pointsToComputeIntoLines;
    }

    @Override
    public Collection<Line> call() throws Exception {
        return null;
    }
}
