package hashmap_demo;


public class Test1 {
    public static void main(String[] args) {
//        //数组的插入和查询时间比较
//        ArrayList<Integer> list=new ArrayList<>();
//        long t1=System.nanoTime();
//        list.add(5);
//        long t2=System.nanoTime();
//        list.get(0);
//        long t3=System.nanoTime();
//        System.out.println("数组插入耗时："+(t2-t1) +"\n"+
//                "数组查询耗时: "+(t3-t2));
//
//        //链表插入和查询时间比较
//        LinkedList<Integer> linkedList=new LinkedList<>();
//        long t4=System.nanoTime();
//        linkedList.add(4);
//        long t5=System.nanoTime();
//        linkedList.get(0);
//        long t6=System.nanoTime();
//        System.out.println("链表插入耗时："+(t5-t4) +"\n"+
//                "链表查询耗时: "+(t6-t5));

//        int[] a=new int[5];
//        a[2]=5;
//        System.out.println(a.length);

//        HashMap<String,String> map=new HashMap<>();
//        map.put()
        String a = "16";
        String b = "32";
        String c = "54";
        String d = "18";
        System.out.println(
                Test1.hashIndex(a) + "\n" + Test1.hashIndex(b) + "\n" + Test1.hashIndex(c) + "\n" + Test1.hashIndex(d)
        );
        Test1.test1();
    }

    static int hashIndex(String k) {
        int i = k.hashCode() % 16;
        return Math.abs(i);
    }

    static void test1() {
        HashMap1<String, String> map1 = new HashMap1<>();
        map1.put("16", "a");
        map1.put("32", "b");
        map1.put("54", "c");
        map1.put("18", "d");
        System.out.println(map1.get("16") + "\n" + map1.get("32") + "\n" +
                map1.get("54") + "\n" + map1.get("18") + "\n");
    }
}
