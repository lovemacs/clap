import clojure.lang.RT;

// 使用java调用clojure函数
public class App01Invoke {
    private static final String MAINCLJ = "snake.clj";

    public static void main(String[] args){
        System.out.println("Java World!" );
        try {
            RT.loadResourceScript(MAINCLJ);
          //  RT.var("com.example.clap", "main").invoke(args);   // 使用java调用clojure函数
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}