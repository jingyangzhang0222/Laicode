/*
Given an array of words and a width maxWidth, format the text such that each line has
exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can
in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of
spaces on a line do not divide evenly between words, the empty slots on the left will be
assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

    O(n)
    O(n)
*/
package laicode;

import java.util.ArrayList;

public class TextJustification {
    public static void main(String args[]) {
        TextJustification test = new TextJustification();
        String[] words = {"Listen", "to", "many,", "speak", "to", "a", "few."};
        ArrayList<String> res = test.fullJustify(words, 6);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> sol = new ArrayList<>();
        if (words == null || words.length == 0) {
            return sol;
        }

        int wordsLength = 0;//所有非空格字母长度之和
        int spaceLength = 0;//所有字符(包括中间空格与最后一个空格)数量之和
        int wordCount = 0;  //单词计数
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            int len = word.length();

            int newWordsLength = wordsLength + len;
            int newSpaceLength = spaceLength + len + 1;

            if (newSpaceLength - 1 <= L) {
                sb.append(word);
                sb.append(' ');
                wordCount++;
                wordsLength = newWordsLength;
                spaceLength = newSpaceLength;
            } else {//newSpaceLength >= L
                // 空格array
                String[] spaces = spaceGenerator(wordCount, L, wordsLength);
                // 去掉最后一个空格
                sb.deleteCharAt(sb.length() - 1);
                // 所有该行有效words
                String[] wordsInLine = sb.toString().split(" ");
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < wordsInLine.length; j++) {
                    tmp.append(wordsInLine[j]);
                    if (j < spaces.length) {
                        tmp.append(spaces[j]);
                    }
                }
                sol.add(tmp.toString());
                sb = new StringBuilder();
                sb.append(word);
                sb.append(' ');
                wordsLength = len;
                spaceLength = len + 1;
                wordCount = 1;
            }
        }
        // 最后一行的处理: 单词之间只需一个空格即可, 多余空格一律插入到最后补齐
        sb.deleteCharAt(sb.length() - 1);//不删除最后一个空格会有corner case
        while (sb.length() < L) {
            sb.append(' ');
        }
        sol.add(sb.toString());

        return sol;
    }

    private String[] spaceGenerator(int wordCount, int L, int wordsLength) {
        // 若只有一个有效word, 只要返回一个用于补齐的空格block即可, 长度为 L - wordsLength
        if (wordCount == 1) {
            StringBuilder space = new StringBuilder();
            for (int i = 0; i < L - wordsLength; i++) {
                space.append(' ');
            }
            return new String[]{space.toString()};
        }
        // space block 数量
        int spaceBlockCount = wordCount - 1;
        // space 数量
        int spaceCount = L - wordsLength;

        //对于每个space block中空格数量不等的情况, 原题要求"尽量 evenly distributed", 多余空格尽量靠左
        // 譬如17个空格分配给 3 个block, 就应该是: 6 6 5
        // basic : 5
        // res: 2
        int basic = spaceCount / spaceBlockCount;
        int res = spaceCount - basic * spaceBlockCount;

        String[] spaces = new String[spaceBlockCount];
        for (int i = 0; i < spaceBlockCount; i++) {
            StringBuilder space = new StringBuilder();
            for (int j = 0; j < basic; j++) {
                space.append(' ');
            }
            //在尽量靠左的地方补齐多余的空格
            if (res-- > 0) {
                space.append(' ');
            }
            spaces[i] = space.toString();
        }
        return spaces;
    }
}
