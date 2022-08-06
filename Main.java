package task7;

/**
 * у нас есть граф из шести островов и мосты построены между 0м и 1м, 1м и 2м, 3м и 4м островами, то из 0го острова можно добраться до двух островов (до 1го и до 2го через 1й), из 3го можно добраться только до одного острова (4го), а из пятого вообще мостов нет. Ответ вашей программы в таком случае должен быть: Сколько достижимо городов для каждого города: [2, 2, 2, 1, 1, 0].
 */
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(8);

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);


        graph.addAdge(0, 1);
        graph.addAdge(0, 2);
        graph.addAdge(1, 2);
        graph.addAdge(2, 5);
        graph.addAdge(2, 3);
        graph.addAdge(3, 4);

        graph.addAdge(6, 7);
        int[] path = graph.calcPaths();
        System.out.print("Достижимо городов для каждого города: ");
        for (int i : path) {
            System.out.printf("%s ", i);
        }

        graph.bypassInBreadh();
    }
}
