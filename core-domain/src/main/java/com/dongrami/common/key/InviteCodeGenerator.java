package com.dongrami.common.key;

import java.util.Random;

public class InviteCodeGenerator implements KeyGenerator {

    @Override
    public String generateKey() {
        FirstInviteCode firstInviteCode = FirstInviteCode.getRandom();
        SecondInviteCode secondInviteCode = SecondInviteCode.getRandom();

        Random random = new Random();
        int randomNumber = random.nextInt(9999) + 1;
        String formattedNumber = String.format("%04d", randomNumber);

        return firstInviteCode.name() + secondInviteCode.name() + "#" + formattedNumber;
    }

    enum FirstInviteCode {
        푸른, 명량한, 달콤한, 산뜻한, 따스한, 차가운, 고색창연한, 온화한, 포근한, 화려한, 아련한, 청명한, 향긋한, 잔잔한, 고요한, 오묘한, 아스란한, 투명한, 부드러운, 그윽한, 나른한;

        public static FirstInviteCode getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    enum SecondInviteCode {
        아침, 햇볕, 여름, 가을, 겨울, 초승달, 윤슬, 이슬, 새벽, 저녁, 들판, 하늘, 노을, 봄, 그늘, 소풍, 바다, 호수, 여행, 거리, 새싹;

        public static SecondInviteCode getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

}
