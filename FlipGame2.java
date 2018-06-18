package leetcode;

public class FlipGame2 {
    public boolean canWin(String s) {
        return canWin(s.toCharArray());
    }
    //canWIn(++++++) == (!canWIn(--++++)) || (!canWin(+--+++))
    //|| (!canWin(++--++)) || (!canWIn(+++--+)) || (!canWin(++++--))
    private boolean canWin(char[] s) {
        for (int i = 0; i + 1 < s.length; i++) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = '-';
                s[i + 1] = '-';
                boolean theOtherPlayerWins = canWin(s);
                s[i + 1] = '+';
                s[i] = '+';
                if (!theOtherPlayerWins) {
                    return true;
                }
            }
        }
        return false;
    }
}
