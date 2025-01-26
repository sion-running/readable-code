package cleancode.minesweeper.tobe;

public class AnotherGame implements GameRunnable {
//    @Override
//    public void initialize() {
        // 초기화가 필요없는 게임이면..?
//    }

    @Override
    public void run() {
         // 게임진행
    }
}

// WHAT I LEARNED
// Game 인터페이스의 구현체인 AnotherGame를 만들어보니,
// 이 게임은 초기화가 필요없는 게임이므로 initialize는 불필요하다.
// 따라서, Game 인터페이스를 분리하고, 게임별로 각각 필요한 기능의 인터페이스를 상속하도록 한다.
