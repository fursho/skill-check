package q004;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        ListManager manager = new ListManager();

        for (int i = 0; i < manager.size() - 1; i++) {
            for (int j = manager.size() - 1; j > i; j--) {
                if (manager.compare(i, j) > 0) {
                    manager.exchange(i, j);
                }
            }
        }

        manager.checkResult();
    }
}
// 完成までの時間: 00時間 02分