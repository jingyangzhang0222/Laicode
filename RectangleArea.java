/*
* Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
    20180522
    223
    medium
    ???
    O(1)
    O(1)
* */
package leetcode;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    /*
    bottom left corner of rectangle1:  (A, B)      (A, D)-------(C, D)
    top right corner of rectangle1:    (C, D)            |     |
    bottom right corner of rectangle1: (C, B)            |     |
    top left corner of rectangle1:     (A, D)      (A, B)-------(C, B)

    bottom left corner of rectangle2:  (E, F)      (E, H)-------(G, H)
    top right corner of rectangle2:    (G, H)            |     |
    bottom right corner of rectangle2: (G, F)            |     |
    top left corner of rectangle2:     (E, H)      (E, F)-------(G, F)

    A: 1 left    B: 1 bottom
    C: 1 right   D: 1 top

    E: 2 left    F: 2 bottom
    G: 2 right   H: 2 top

                  _________________
                 |        |        |
                 |        |        |
                 |________|________|

                            ____________ CD
                           |            |
                       ____|___ GH      |
                       |   |   |        |
                       | AB|___|________|
                     EF|_______|

                            ____________ GH
                           |            |
                       ____|___ CD      |
                       |   |   |        |
                       | EF|___|________|
                     AB|_______|

                 AD ___________
                   |           |
                   |    EH_____|_____
                   |_____|_____|     |
                         |     CB    |
                         |___________|
                                      GF
                 EH ___________
                   |           |
                   |    AD_____|_____
                   |_____|_____|     |
                         |     GF    |
                         |___________|
                                      CB
      */
        long a = A, b = B, c = C, d = D, e = E, f = F, g = G, h = H;
        long s1 = (c - a) * (d - b) + (g - e) * (h - f);
        long s2 = (Math.min(c, g) - Math.max(a, e) <= 0 || Math.min(d, h) - Math.max(b, f) <= 0) ? 0 : (Math.min(c, g) - Math.max(a, e)) * (Math.min(d, h) - Math.max(b, f));
        return (int)(s1 - s2);
    }
}
