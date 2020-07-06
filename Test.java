import java.util.*;

public class Test {
    public static void main1(String[] args) {

        // 给定一句英语，要求你编写程序，将句中所有单词的顺序颠倒输出。

        // 输入描述:
        // 测试输入包含一个测试用例，在一行内给出总长度不超过 80 的字符串。
        // 字符串由若干单词和若干空格组成，其中单词是由英文字母（大小写有区分）组成的字符串，
        // 单词之间用 1 个空格分开，输入保证句子末尾没有多余的空格。
        // 输出描述:
        // 每个测试用例的输出占一行，输出倒序后的句子。
        
        // 示例:
        // 输入
        // Hello World Here I Come
        // 输出
        // Come I Here World Hello

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine(); //一定是 nextLine();
        String[] strs = str.split(" ");
        for (int i = strs.length - 1; i > 0; i--) {
            System.out.print(strs[i] + " ");
        }
        System.out.print(strs[0]);
    }

    public static void main2(String[] args) {

        // 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
        // 处理:
        // 1. 记录最多 8 条错误记录，对相同的错误记录
        // (即文件名称和行号完全匹配)只记录一条，错误计数增加;
        // (文件所在的目录不同，文件名和行号相同也要合并)
        // 2. 超过 16 个字符的文件名称，只记录文件的最后有效 16 个字符;
        // (如果文件名不同，而只是文件名的后 16 个字符和行号相同，也不要合并)
        // 3. 输入的文件可能带路径，记录文件名称不能带路径

        // 输入描述:
        // 一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
        // 文件路径为 windows 格式
        // 如：E:\V1R2\product\fpgadrive.c 1325
        //输出描述:
        // 将所有的记录统计并将结果输出
        // 格式：文件名代码行数数目，一个空格隔开
        // 如: fpgadrive.c 1325 1

        // 结果根据数目从多到少排序，数目相同的情况下，按照输入第一次出现顺序排序.
        // 如果超过 8 条记录，则只输出前 8 条记录.
        // 如果文件名的长度超过 16 个字符，则只输出后 16 个字符.

        // 示例:
        // 输入
        // E:\V1R2\product\fpgadrive.c 1325
        // 输出
        // fpgadrive.c 1325 1

        Scanner in = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<>(); // 防止新一次输入时链表清空
        while (in.hasNext()) {
            String path = in.next();
            int id = path.lastIndexOf('\\'); // 找到文件名
            // lastIndexOf(int ch): 返回指定字符的最后一次出现的字符串中的索引
            // 如果找不到说明输入只有文件名, 没有路径, 可直接输出.
            String fileName = id < 0 ? path : path.substring(id + 1);
            // substring(int beginIndex): 返回一个字符串，该字符串是此字符串的子字符串
            int lineNum = in.nextInt(); // 代码行数
            String key = fileName + " " + lineNum;
            // 计算出现次数(方便排序)
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }
        in.close();

        // 对记录进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet()); // 将 Map 中的数据转存 List 中
        // 重写 Collections 工具类的 sort()  方法，
        // 对 list 中的 entry(条目)，按照 value 值进行排列
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            // 根据指定的比较器引起的顺序对指定的列表进行排序
            public int compare(Map.Entry<String, Integer> arg0, Map.Entry<String, Integer> arg1) {
                // 根据题目, 降序排列(arg1 -> arg0)
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });
        // 只输出前八条
        int count = 0;
        for (Map.Entry<String, Integer> m : list) {
            count++;
            if (count <= 8) {
                String[] str = m.getKey().split(" ");
                // 分开文件名 和 代码行数
                // str[0] 仅包含文件名
                String len = str[0].length() > 16 ? str[0]. substring(str[0].length() - 16) : str[0];
                System.out.println(len + " " + str[1] + " " + m.getValue());
                // 文件名 代码行数 数目
            }else {
                break;
            }
        }
    }
}
