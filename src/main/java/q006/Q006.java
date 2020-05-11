package q006;

import q006.value.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Q006 空気を読んで改修
 *
 * 標準入力から「逆ポーランド記法」で記載された1行の入力を受け取り、その計算結果を出力する処理を実装してください。
 * 実装するのは四則演算（+ - * /）です。
 *
 * https://ja.wikipedia.org/wiki/%E9%80%86%E3%83%9D%E3%83%BC%E3%83%A9%E3%83%B3%E3%83%89%E8%A8%98%E6%B3%95
 *
 * ただし、現状は以下の実装が終わっています。
 * - 逆ポーランド記法を分解して、計算しやすい値リストに変換する処理の一部（Q006.parseLine）
 * - 計算しやすい値として管理するためのクラス群の一部（IValue,DecimalValue,PlusValue）
 *
 * 途中まで終わっている実装を上手く流用しながら、残りの処理を実装してください。
 * エラー入力のチェックは不要です。
 *
 * 実行例：
 *
 * 入力） 3 1.1 0.9 + 2.0 * -
 * 出力） -1
 * （または -1.00 など、小数点に0がついてもよい）
 */
public class Q006 {

    public static void main(String[] args) {
        System.out.print("入力） ");
        Scanner scan = new Scanner(System.in);
        Stack<BigDecimal> stack = new Stack<BigDecimal>();

        List<IValue> result = parseLine(scan.nextLine());

        for (int i = 0; i < result.size(); i++) {
            result.get(i).execute(stack);
        }

        System.out.println("出力） " + stack.pop());
    }

    /**
     * 逆ポーランドで記載された1行のテキストを分解する
     * @param lineText 1行テキスト
     * @return 分解された値リスト
     */
    private static List<IValue> parseLine(String lineText) {
        List<IValue> resultList = new ArrayList<>();
        // 空白文字で区切ってループする
        for (String text: lineText.split("[\\s]+")) {
            // TODO 一部処理だけ実装
            switch (text) {
                case "/":   // 割り算
                    resultList.add(new DivideValue());
                    break;
                case "*":   // 掛け算
                    resultList.add(new MultiplyValue());
                    break;
                case "+":   // 足し算
                    resultList.add(new PlusValue());
                    break;
                case "-":   // 引き算
                    resultList.add(new MinusValue());
                    break;
                default:    // その他は数値として扱う
                    resultList.add(new DecimalValue(text));
                    break;
            }
        }
        return resultList;
    }
}
// 完成までの時間: 00時間 39分