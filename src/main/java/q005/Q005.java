package q005;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import q003.Q003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {
        try (InputStream is = openDataFile()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Stream<String> lines = br.lines();

            List<WorkData> workDataList = lines.skip(1)
                    .map(line -> line.split(","))
                    .map(elem -> new WorkData(elem[0], elem[1], elem[2], elem[3], Integer.parseInt(elem[4])))
                    .collect(Collectors.toList());

            // 役職別
            Map<String, Long> positionMap = workDataList.stream()
                    .collect(Collectors.groupingBy(workData -> workData.getPosition(),
                            Collectors.summingLong(WorkData::getWorkTime)));
            printWorkTime(positionMap);

            // Pコード別
            Map<String, Long> codeMap = workDataList.stream()
                    .collect(Collectors.groupingBy(workData -> workData.getPCode(),
                            Collectors.summingLong(WorkData::getWorkTime)));
            printWorkTime(codeMap);

            // 社員番号別
            Map<String, Long> numberMap = workDataList.stream()
                    .collect(Collectors.groupingBy(workData -> workData.getNumber(),
                            Collectors.summingLong(WorkData::getWorkTime)));
            printWorkTime(numberMap);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printWorkTime(Map<String, Long> map) {
        map.entrySet().stream()
                .map(m -> m.getKey() + ": " + m.getValue() / 60 + "時間" + String.format("%02d分", m.getValue() % 60))
                .forEach(System.out::println);
    }
}
// 完成までの時間: 0時間 46分