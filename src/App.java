public class App {
    public static void main(String[] args) {
        Metodos a = new Metodos();

        int[] list1 = Generador.generate(10000);
        int[] list2 = Generador.generate(50000);
        int[] list3 = Generador.generate(100000);

        int[] bubble1 = list1.clone();
        int[] bubble2 = list2.clone();
        int[] bubble3 = list3.clone();

        int[] merge1 = list1.clone();
        int[] merge2 = list2.clone();
        int[] merge3 = list3.clone();

        int[] lib1 = list1.clone();
        int[] lib2 = list2.clone();
        int[] lib3 = list3.clone();

        int[] cmp1 = list1.clone();
        int[] cmp2 = list2.clone();
        int[] cmp3 = list3.clone();


        Results resBubble1 = Benchmark.medir(() -> a.sortBubble(bubble1), "Burbuja", "10000");
        Results resBubble2 = Benchmark.medir(() -> a.sortBubble(bubble2), "Burbuja", "50000");
        Results resBubble3 = Benchmark.medir(() -> a.sortBubble(bubble3), "Burbuja", "100000");


        Results resMerge1 = Benchmark.medir(() -> a.sortMerge(merge1), "Merge Sort", "10000");
        Results resMerge2 = Benchmark.medir(() -> a.sortMerge(merge2), "Merge Sort", "50000");
        Results resMerge3 = Benchmark.medir(() -> a.sortMerge(merge3), "Merge Sort", "100000");


        Results resLib1 = Benchmark.medir(() -> a.sortLibrary(lib1), "Arrays.sort", "10000");
        Results resLib2 = Benchmark.medir(() -> a.sortLibrary(lib2), "Arrays.sort", "50000");
        Results resLib3 = Benchmark.medir(() -> a.sortLibrary(lib3), "Arrays.sort", "100000");


        Results resCmp1 = Benchmark.medir(() -> a.sortLibraryComparator(cmp1), "Arrays.sort + Comparator", "10000");
        Results resCmp2 = Benchmark.medir(() -> a.sortLibraryComparator(cmp2), "Arrays.sort + Comparator", "50000");
        Results resCmp3 = Benchmark.medir(() -> a.sortLibraryComparator(cmp3), "Arrays.sort + Comparator", "100000");

        System.out.println(resBubble1);
        System.out.println(resBubble2);
        System.out.println(resBubble3);
        System.out.println();

        System.out.println(resMerge1);
        System.out.println(resMerge2);
        System.out.println(resMerge3);
        System.out.println();

        System.out.println(resLib1);
        System.out.println(resLib2);
        System.out.println(resLib3);
        System.out.println();

        System.out.println(resCmp1);
        System.out.println(resCmp2);
        System.out.println(resCmp3);
    }
}