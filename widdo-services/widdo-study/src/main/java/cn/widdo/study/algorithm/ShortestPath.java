package cn.widdo.study.algorithm;

/**
 * ShortestPath.
 * <p>
 * 最短路径算法
 *
 * @author XYL
 * @date 2023/05/25 14:35
 * @since 305.2.0.1
 */
public class ShortestPath {

    protected ShortestPath() {
        throw new UnsupportedOperationException(ShortestPath.class.getName() + " can`t be instance.");
    }

    /**
     * 不存在.
     */
    private static final int M = 100000;

    /**
     * 顶点.
     */
    private static char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F'};


//    private static List<String> vertex = List.of("A", "B", "C", "D", "E", "F");

    /**
     * 邻接矩阵.
     */
    private static int[][] matrix = new int[vertex.length][vertex.length];

    static {
        matrix[0] = new int[]{0, 50, 50, 100, M, M};
        matrix[1] = new int[]{50, 0, M, 40, M, M};
        matrix[2] = new int[]{50, M, 0, 40, 80, M};
        matrix[3] = new int[]{100, 40, 40, 0, 30, 80};
        matrix[4] = new int[]{M, M, 80, 30, 0, 40};
        matrix[5] = new int[]{M, M, M, 80, 40, 0};
    }

    public static class Dijkstra {

        /**
         * 参数adjMatrix:为图的权重矩阵，权值为-1的两个顶点表示不能直接相连
         * 函数功能：返回顶点0到其它所有顶点的最短距离，其中顶点0到顶点0的最短距离为0.
         *
         * @param matrix
         * @param source
         * @author XYL
         * @date 2023/06/27 17:30:00
         */
        public static void getShortestPaths(int[][] matrix, int source) {
            //最短路径长度
            int[] shortest = new int[matrix.length];
            //判断该点的最短路径是否求出
            int[] visited = new int[matrix.length];
            //存储输出路径
            String[] path = new String[matrix.length];

            //初始化输出路径
            for (int i = 0; i < matrix.length; i++) {
                path[i] = vertex[source] + "->" + vertex[i];
            }

            //初始化起点，将起点放入S
            shortest[source] = 0;
            visited[source] = 1;

            for (int i = 1; i < matrix.length; i++) {       //i从1开始，因为起点已经加入S了
                int min = M;
                int index = -1;

                //找出某节点到起点路径最短
                for (int j = 0; j < matrix.length; j++) {
                    //已经求出最短路径的节点不需要再加入计算并判断加入节点后是否存在更短路径
                    if (visited[j] == 0 && matrix[source][j] < min) {
                        min = matrix[source][j];
                        index = j;
                    }
                }

                //更新最短路径，标记起点到该节点的最短路径已经求出
                shortest[index] = min;
                visited[index] = 1;

                //更新从index跳到其它节点的较短路径
                for (int m = 0; m < matrix.length; m++) {
                    if (visited[m] == 0 && matrix[source][index] + matrix[index][m] < matrix[source][m]) {
                        matrix[source][m] = matrix[source][index] + matrix[index][m];
                        path[m] = new StringBuilder().append(path[index]).append("->").append(vertex[m]).toString();
                    }
                }
            }

            //打印最短路径
            for (int i = 0; i < matrix.length; i++) {
                if (i != source) {
                    if (shortest[i] == M) {
                        System.out.println(vertex[source] + "到" + vertex[i] + "不可达");
                    } else {
                        System.out.println(vertex[source] + "到" + vertex[i] + "的最短路径为：" + path[i] + "，最短距离是：" + shortest[i]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        ShortestPath.Dijkstra.getShortestPaths(matrix, 0);

    }
}
