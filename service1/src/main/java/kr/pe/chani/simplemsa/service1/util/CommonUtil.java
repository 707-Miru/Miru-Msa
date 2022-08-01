package kr.pe.chani.simplemsa.service1.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class CommonUtil {

    /**
     * 히스트릭스가 장애 대응을 잘 하는지 알아보기 위해 임의로 지연을 발생시키는 메소드
     * 호출 3회중 랜덤 1회 11초 지연
     */
    public static void randomlyRunLong() {
        final int BOUND = 3;
        final long MILLIS = 11000;
        randomlyRunLong(BOUND, MILLIS);
    }

    /**
     * 호출 {bound}회중 랜덤 1회 {millis}ms 지연
     */
    public static void randomlyRunLong(int bound, long millis){
        Random rand = new Random();

        int randomNum = rand.nextInt((bound - 1) + 1) + 1;

        if (randomNum==bound) sleep(millis);
    }

    private static void sleep(long millis){
        try {
            log.info("Delay .....");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
