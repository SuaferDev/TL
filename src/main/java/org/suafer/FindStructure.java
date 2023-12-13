package org.suafer;

import java.util.List;

public class FindStructure {
    public static void findStructure(Field[][] map, int startX, int startY, int endX, int endY, List<Structure> structures) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);
        int sx = (startX < endX) ? 1 : -1;
        int sy = (startY < endY) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (map[startX][startY].getStructure() != null) {
                structures.add(map[startX][startY].getStructure());
            }
            if (startX == endX && startY == endY) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                startX = startX + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                startY = startY + sy;
            }
        }
    }
}
