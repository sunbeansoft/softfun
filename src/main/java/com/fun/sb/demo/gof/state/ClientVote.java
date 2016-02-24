package com.fun.sb.demo.gof.state;

public class ClientVote {

    public static void main(String[] args) {

        VoteManager vm = new VoteManager();
        for (int i = 0; i < 9; i++) {
            vm.vote("u1", "A");
        }
    }

}